package com.example.carwash.service;

import com.example.carwash.dto.order.OrderCreateDto;
import com.example.carwash.dto.order.OrderDto;
import com.example.carwash.dto.order.OrderUpdateDto;
import com.example.carwash.dto.orderBill.OrderBillDto;
import com.example.carwash.exception.AccessDeniedException;
import com.example.carwash.exception.AvailableBoxNotFoundException;
import com.example.carwash.exception.EntityNotFoundException;
import com.example.carwash.exception.OrderCannotBeChangedException;
import com.example.carwash.model.*;
import com.example.carwash.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.example.carwash.model.OrderStatus.*;
import static com.example.carwash.model.Role.ADMIN;
import static com.example.carwash.model.Role.OPERATOR;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderBillService orderBillService;
    private final OperatorService operatorService;
    private final UserService userService;
    private final OfferService offerService;
    private final BoxService boxService;
    private final Integer MIN_ADMIN_DISCOUNT = 0;
    private final Integer MAX_ADMIN_DISCOUNT = 100;
    private final Integer MIN_BEFORE_ORDER = 20;

    //TODO specifications
    public List<OrderDto> showFilteredOrders(Long boxId, LocalDateTime dateTime) {
        return orderRepo.findAll().stream().map(OrderDto::toDto).toList();
    }


    @Transactional
    public OrderDto cancel(Long id) {
        User user = userService.getCurrentUser();
        Order order = getOrder(id);
        isAccessGranted(order, user);
        if (!isAccessGranted(order, user)) {
            throw new AccessDeniedException();
        }
        if (order.getStatus().equals(CANCELLED) || order.getStatus().equals(FINISHED)) {
            throw new OrderCannotBeChangedException(id, order.getStatus(), CANCELLED);
        }
        order.setStatus(CANCELLED);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    private boolean isAccessGranted(Order order, User user) {
        if (user == order.getUser() || user.getRole().equals(ADMIN)) {
            return true;
        } else if (user.getRole().equals(Role.OPERATOR)) {
            Operator operator = getCurrentOperator();
            return operator.getBox().equals(order.getBox());
        }
        return false;
    }

    @Transactional
    public OrderDto create(OrderCreateDto orderCreateDto) {
        LocalDateTime dateTime = orderCreateDto.getDateTime();
        if (dateTime.isBefore(LocalDateTime.now().plus(Duration.ofMinutes(MIN_BEFORE_ORDER)))) {
            throw new DateTimeException("Ordering is no longer available at this time");
        }
        User user = userService.getCurrentUser();
        Offer offer = offerService.getOffer(orderCreateDto.getOfferId());
        Box box = boxService.getAvailableBox(offer, dateTime);
        if (box == null) {
            throw new AvailableBoxNotFoundException();
        }
        Integer duration = getDuration(offer, box);
        Order order = new Order(user, offer, OrderStatus.SUBMITTED,
                dateTime, duration, offer.getPrice(), box);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    private int getDuration(Offer offer, Box box) {
        return Math.toIntExact(Math.round(offer.getDuration() * box.getTimeCoefficient()));
    }


    public List<OrderDto> getUserOrders(Long id) {
        User user = userService.getCurrentUser();
        if (Objects.equals(user.getId(), id) || user.getRole().equals(ADMIN)) {
            return user.getOrders().stream().map(OrderDto::toDto).toList();
        }
        throw new AccessDeniedException();
    }

    public Order getOrder(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", "id", id.toString()));
    }


    @Transactional
    public OrderDto update(Long id, OrderUpdateDto orderUpdateDto) {
        User user = userService.getCurrentUser();
        Order order = getOrder(id);
        if (!isAccessGranted(order, user)) {
            throw new AccessDeniedException();
        }
        if (!(order.getStatus().equals(SUBMITTED) || (order.getStatus().equals(CHECKED_IN)))) {
            throw new OrderCannotBeChangedException(id, order.getStatus());
        }
        if (orderUpdateDto.getOfferId() != null) {
            order.setOffer(offerService.getOffer(orderUpdateDto.getOfferId()));
        }
        if (orderUpdateDto.getDateTime() != null) {
            order.setDateTime(orderUpdateDto.getDateTime());
        }
        Box box = boxService.getAvailableBox(order.getOffer(), order.getDateTime());
        Integer duration = getDuration(order.getOffer(), box);
        order.setBox(box);
        order.setDuration(duration);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    @Transactional
    public void checkIn(Long id) {
        Order order = getOrder(id);
        User user = userService.getCurrentUser();
        if (order.getUser() != user) {
            throw new AccessDeniedException();
        }
        if (!order.getStatus().equals(SUBMITTED)) {
            throw new OrderCannotBeChangedException(id, order.getStatus(), CHECKED_IN);
        }
        order.setStatus(CHECKED_IN);
        orderRepo.save(order);
    }


    @Transactional
    public OrderBillDto finish(Long id) {
        Order order = getOrder(id);
        if (userService.getCurrentUser().getRole().equals(OPERATOR)) {
            Operator operator = getCurrentOperator();
            if (!operator.getBox().equals(order.getBox())) {
                throw new AccessDeniedException();
            }
        }
        if (!(order.getStatus().equals(CHECKED_IN))) {
            throw new OrderCannotBeChangedException(id, order.getStatus(), FINISHED);
        }
        order.setStatus(OrderStatus.FINISHED);
        orderRepo.save(order);
        OrderBill orderBill = orderBillService.createBill(order);
        return OrderBillDto.toDto(orderBill);
    }

    public OrderDto setDiscount(Long id, Integer discount) {
        Order order = getOrder(id);
        if (userService.getCurrentUser().getRole().equals(OPERATOR)) {
            Operator operator = getCurrentOperator();
            if (!operator.getBox().equals(order.getBox())) {
                throw new AccessDeniedException();
            } else if (operator.getMinDiscount() > discount || operator.getMaxDiscount() < discount) {
                throw new OrderCannotBeChangedException(operator.getMinDiscount(), operator.getMaxDiscount());
            }
        } else if (discount < MIN_ADMIN_DISCOUNT || discount > MAX_ADMIN_DISCOUNT) {
            throw new OrderCannotBeChangedException(MIN_ADMIN_DISCOUNT, MAX_ADMIN_DISCOUNT);
        }
        order.setDiscount(discount);
        return OrderDto.toDto(order);
    }

    public Operator getCurrentOperator() {
        User user = userService.getCurrentUser();
        return operatorService.getOperatorByUserId(user.getId());
    }

    public void cancelNotCheckedInOrders() {
        List<Order> closestOrders = orderRepo.findAllByStatusAndDateTime(SUBMITTED, LocalDateTime.now());
    }

    public void cancelClosestNotCheckedInOrders() {
    }
}

package com.example.carwash.service;

import com.example.carwash.dto.order.OrderCreateDto;
import com.example.carwash.dto.order.OrderDto;
import com.example.carwash.dto.order.OrderUpdateDto;
import com.example.carwash.dto.orderBill.OrderBillDto;
import com.example.carwash.exception.*;
import com.example.carwash.model.*;
import com.example.carwash.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.carwash.model.OrderStatus.*;
import static com.example.carwash.model.Role.ADMIN;
import static com.example.carwash.model.Role.OPERATOR;

@Slf4j
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
    private final Integer MINUTES_BEFORE_ORDER = 10;
    private final Long HOURS_CHECKIN_AVAILABLE = 2L;


    @Transactional
    public OrderDto cancel(Long id) {
        User user = userService.getCurrentUser();
        Order order = getOrder(id);
        if (isAccessDenied(order, user)) {
            throw new CustomAccessDeniedException();
        }
        if (order.getStatus() == CANCELLED || order.getStatus() == FINISHED) {
            throw new OrderCannotBeChangedException(id, order.getStatus(), CANCELLED);
        }
        order.setStatus(CANCELLED);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    private boolean isAccessDenied(Order order, User user) {
        if (user.getId().equals(order.getUser().getId()) || user.getRole() == ADMIN) {
            return false;
        }
        if (user.getRole() == OPERATOR) {
            Operator operator = getCurrentOperator(user);
            return isOrderRelatesToOperatorBox(order, operator);
        }
        return true;
    }

    @Transactional
    public OrderDto create(OrderCreateDto orderCreateDto) {
        LocalDateTime dateTime = orderCreateDto.getDateTime();
        if (dateTime.isBefore(LocalDateTime.now().plus(Duration.ofMinutes(MINUTES_BEFORE_ORDER)))) {
            throw new DateTimeException("Ordering is no longer available at this time");
        }
        User user = userService.getCurrentUser();
        Offer offer = offerService.getOffer(orderCreateDto.getOfferId());
        Box box = boxService.getAvailableBox(offer, dateTime);
        if (box == null) {
            throw new AvailableBoxNotFoundException();
        }
        int minutesDuration = getMinutesDuration(offer, box);
        Order order = new Order(user, offer, SUBMITTED,
                dateTime, minutesDuration, offer.getPrice(), box);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    private int getMinutesDuration(Offer offer, Box box) {
        return Math.toIntExact(Math.round(offer.getDuration() * box.getTimeCoefficient()));
    }


    public List<OrderDto> getUserOrders(Long id) {
        User currentUser = userService.getCurrentUser();
        if (id.equals(currentUser.getId()) || currentUser.getRole() == ADMIN) {
            User user = userService.getUser(id);
            return user.getOrders().stream().map(OrderDto::toDto).toList();
        }
        throw new CustomAccessDeniedException();
    }

    public Order getOrder(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", "id", id.toString()));
    }


    @Transactional
    public OrderDto update(Long id, OrderUpdateDto orderUpdateDto) {
        User user = userService.getCurrentUser();
        Order order = getOrder(id);
        if (isAccessDenied(order, user)) {
            throw new CustomAccessDeniedException();
        }
        if (order.getStatus() != SUBMITTED && order.getStatus() != CHECKED_IN) {
            throw new OrderCannotBeChangedException(id, order.getStatus());
        }
        if (orderUpdateDto.getOfferId() != null) {
            order.setOffer(offerService.getOffer(orderUpdateDto.getOfferId()));
        }
        if (orderUpdateDto.getDateTime() != null) {
            order.setDateTime(orderUpdateDto.getDateTime());
        }
        Box box = boxService.getAvailableBox(order.getOffer(), order.getDateTime());
        if (box == null) {
            throw new AvailableBoxNotFoundException();
        }
        Integer minutesDuration = getMinutesDuration(order.getOffer(), box);
        order.setBox(box);
        order.setDuration(minutesDuration);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    @Transactional
    public OrderDto checkIn(Long id) {
        User user = userService.getCurrentUser();
        Order order = getOrder(id);
        if (isAccessDenied(order, user)) {
            throw new CustomAccessDeniedException();
        }
        if (order.getStatus() != SUBMITTED) {
            throw new OrderCannotBeChangedException(id, order.getStatus(), CHECKED_IN);
        }
        LocalDateTime endDateTime = order.getDateTime().plusMinutes(order.getDuration());
        if (endDateTime.isBefore(LocalDateTime.now())) {
            throw new CheckInNotAvailableException("already");
        }
        if (LocalDateTime.now().plusHours(HOURS_CHECKIN_AVAILABLE).isBefore(order.getDateTime())) {
            throw new CheckInNotAvailableException("yet");
        }
        order.setStatus(CHECKED_IN);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }


    @Transactional
    public OrderBillDto finish(Long id) {
        Order order = getOrder(id);
        User user = userService.getCurrentUser();
        if (user.getRole() == OPERATOR) {
            Operator operator = getCurrentOperator(user);
            if (!isOrderRelatesToOperatorBox(order, operator)) {
                throw new CustomAccessDeniedException();
            }
        }
        if (order.getStatus() != CHECKED_IN) {
            throw new OrderCannotBeChangedException(id, order.getStatus(), FINISHED);
        }
        order.setStatus(FINISHED);
        orderRepo.save(order);
        OrderBill orderBill = orderBillService.createBill(order);
        return OrderBillDto.toDto(orderBill);
    }

    private boolean isOrderRelatesToOperatorBox(Order order, Operator operator) {
        return operator.getBox().getId().equals(order.getBox().getId());
    }

    @Transactional
    public OrderDto setDiscount(Long id, Integer discount) {
        Order order = getOrder(id);
        User user = userService.getCurrentUser();
        if (user.getRole() == OPERATOR) {
            Operator operator = getCurrentOperator(user);
            if (!isOrderRelatesToOperatorBox(order, operator)) {
                throw new CustomAccessDeniedException();
            }
            Integer minDiscount = operator.getMinDiscount();
            Integer maxDiscount = operator.getMaxDiscount();
            if (minDiscount == null || maxDiscount == null || discount < minDiscount || discount > maxDiscount) {
                throw new OrderCannotBeChangedException(operator.getMinDiscount(), operator.getMaxDiscount());
            }
        } else if (discount < MIN_ADMIN_DISCOUNT || discount > MAX_ADMIN_DISCOUNT) {
            throw new OrderCannotBeChangedException(MIN_ADMIN_DISCOUNT, MAX_ADMIN_DISCOUNT);
        }
        order.setDiscount(discount);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    public Operator getCurrentOperator(User currentUser) {
        return operatorService.getOperatorByUser(currentUser);
    }

    @Transactional
    public void cancelNotCheckedInOrders() {
        log.info("Daily automatic cancelling started");
        cancelOrders(orderRepo.findAllBySubmittedStatusAndDateTime(LocalDate.now()));
    }

    @Transactional
    public void cancelClosestNotCheckedInOrders() {
        cancelOrders(orderRepo.findAllClosestBySubmittedStatusAndDateTime(LocalDateTime.now()));
    }

    @Transactional
    void cancelOrders(List<Order> orders) {
        for (Order order : orders) {
            order.setStatus(CANCELLED);
            orderRepo.save(order);
            log.info("Order " + order.getId().toString() + " was automatically cancelled");
        }
    }

    public Page<OrderDto> getOrders(Pageable pageable) {
        return orderRepo.findAll(pageable).map(OrderDto::toDto); //todo
    }

}

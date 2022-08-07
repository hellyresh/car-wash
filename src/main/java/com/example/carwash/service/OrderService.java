package com.example.carwash.service;

import com.example.carwash.dto.order.OrderCreateDto;
import com.example.carwash.dto.order.OrderDto;
import com.example.carwash.exception.EntityNotFoundException;
import com.example.carwash.model.*;
import com.example.carwash.repository.BoxRepo;
import com.example.carwash.repository.OfferRepo;
import com.example.carwash.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderBillService orderBillService;
    private final OfferRepo offerRepo;
    private final BoxRepo boxRepo;

    //TODO specifications
    public List<OrderDto> showFilteredOrders(Long boxId, LocalDateTime dateTime) {
        return orderRepo.findAll().stream().map(OrderDto::toDto).toList();
    }


    @Transactional
    public OrderDto cancel(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", id));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    @Transactional
    public OrderDto cancel(Long id, User currentUser) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", id));

        order.setStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    @Transactional
    public OrderDto create(OrderCreateDto orderCreateDto, User currentUser) {



        Offer offer = offerRepo.findById(orderCreateDto.getOfferId())
                .orElseThrow(() -> new EntityNotFoundException("Offer", orderCreateDto.getOfferId()));

        //todo automatic algorithm
        Box box = new Box();
        Duration duration = calculateDuration(offer.getDuration(), box.getTimeCoefficient());
        LocalDateTime dateTime = LocalDateTime.now();
        //TODO current user

        Order order = new Order(currentUser, offer, OrderStatus.SUBMITTED,
                dateTime, duration, offer.getPrice(), box);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    public List<OrderDto> getUserOrders(User currentUser) {
        return currentUser.getOrders().stream().map(OrderDto::toDto).toList();
    }

    //TODO validation date/time
//    @Transactional
//    public OrderDto update(Long id, OrderUpdateByUserDto orderUpdateDto) {
//
//        Order order = orderRepo.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Order", id));
//        if (orderUpdateDto.getOfferId() != null) {
//            Offer offer = offerRepo.findById(orderUpdateDto.getOfferId())
//                    .orElseThrow(() -> new EntityNotFoundException("Order", id));
//        }
//        if (orderUpdateDto.getDate() != null) {
//            order.setDateTime(orderUpdateDto.getDateTime());
//        }
//        //todo automatic algorithm
//        Box box = new Box();
//        Duration duration = calculateDuration(offer.getDuration(), box.getTimeCoefficient();
//        order.setDuration(calculateDuration());
//
//        //todo date time validation + box
//
//        orderRepo.save(order);
//
//        return OrderDto.toDto(order);
//    }

    //todo validation date/time and finished endpoint with discount
//    @Transactional
//    public OrderDto update(Long id, OrderUpdateByOperatorDto orderUpdateDto) {
//
//        Order order = orderRepo.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Order", id));
//
//        if (orderUpdateDto.getStatus() != null) {
//            order.setStatus(orderUpdateDto.getStatus());
//        }
//        if (orderUpdateDto.getDate() != null) {
//            order.setDate(orderUpdateDto.getDate());
//        }
//        if (orderUpdateDto.getStartTime() != null) {
//            order.setStartTime(orderUpdateDto.getStartTime());
//        }
//        if (orderUpdateDto.getOfferId() != null) {
//            Offer offer = offerRepo.findById(orderUpdateDto.getOfferId())
//                    .orElseThrow(() -> new EntityNotFoundException("Offer", orderUpdateDto.getOfferId()));
//            order.setOffer(offer);
//        }
//        if (orderUpdateDto.getDiscount() != null) {
//            order.setDiscount(orderUpdateDto.getDiscount());
//        }
//
//        orderRepo.save(order);
//
//        return OrderDto.toDto(order);
//    }

    //todo current operator above
    public List<OrderDto> getBoxOrders() {
        return orderRepo.findAll().stream().map(OrderDto::toDto).toList();
    }

    //todo
    public List<OrderDto> getOrdersByBoxId(String id) {
        return null;
    }

    @Transactional
    public String checkIn(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", id));
        order.setStatus(OrderStatus.CHECKED_IN);
        orderRepo.save(order);
        //todo move to FINISHED & delete string?
        orderBillService.createBill(order);
        return "You are successfully checked in";
    }

    private Duration calculateDuration(Duration baseDuration, Double timeCoefficient) {
        return Duration.ofMinutes(Math.round(timeCoefficient * baseDuration.toMinutes()));
    }


}

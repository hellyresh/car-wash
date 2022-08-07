package com.example.carwash.service;

import com.example.carwash.dto.order.OrderCreateDto;
import com.example.carwash.dto.order.OrderDto;
import com.example.carwash.dto.order.OrderUpdateByOperatorDto;
import com.example.carwash.dto.order.OrderUpdateByUserDto;
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
import java.time.LocalTime;
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
    public OrderDto create(OrderCreateDto orderCreateDto, User currentUser) {

        //todo automatic algorithm
        Box box = new Box();

        Offer offer = offerRepo.findById(orderCreateDto.getOfferId())
                .orElseThrow(() -> new EntityNotFoundException("Offer", orderCreateDto.getOfferId()));

        //TODO current user

        Order order = new Order(currentUser, offer, OrderStatus.SUBMITTED,
                orderCreateDto.getDate(), orderCreateDto.getStartTime(),
                countEndTime(offer.getDuration(), box.getTimeCoefficient(), orderCreateDto.getStartTime()), box);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    public List<OrderDto> getUserOrders(User currentUser) {
        return currentUser.getOrders().stream().map(OrderDto::toDto).toList();
    }

    //TODO validation date/time
    @Transactional
    public OrderDto update(Long id, OrderUpdateByUserDto orderUpdateDto) {

        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", id));

        if (orderUpdateDto.getDate() != null) {
            order.setDate(orderUpdateDto.getDate());
        }
        if (orderUpdateDto.getStartTime() != null) {
            order.setStartTime(orderUpdateDto.getStartTime());
        }

        //todo date time validation + box

        orderRepo.save(order);

        return OrderDto.toDto(order);
    }

    //todo validation date/time and price/discount counting
    @Transactional
    public OrderDto update(Long id, OrderUpdateByOperatorDto orderUpdateDto) {

        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", id));

        if (orderUpdateDto.getStatus() != null) {
            order.setStatus(orderUpdateDto.getStatus());
        }
        if (orderUpdateDto.getDate() != null) {
            order.setDate(orderUpdateDto.getDate());
        }
        if (orderUpdateDto.getStartTime() != null) {
            order.setStartTime(orderUpdateDto.getStartTime());
        }
        if (orderUpdateDto.getOfferId() != null) {
            Offer offer = offerRepo.findById(orderUpdateDto.getOfferId())
                    .orElseThrow(() -> new EntityNotFoundException("Offer", orderUpdateDto.getOfferId()));
            order.setOffer(offer);
        }
        if (orderUpdateDto.getDiscount() != null) {
            order.setDiscount(orderUpdateDto.getDiscount());
        }

        orderRepo.save(order);

        return OrderDto.toDto(order);
    }

    //todo current operator
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
        orderBillService.createBill(order);
        return "You are successfully checked in";
    }

    private LocalTime countEndTime(Duration baseDuration, Double timeCoefficient, LocalTime startTime) {
        long durationInMinutes = Math.round(timeCoefficient * baseDuration.toMinutes());
        return startTime.plusMinutes(durationInMinutes);
    }
}

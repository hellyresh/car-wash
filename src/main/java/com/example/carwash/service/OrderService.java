package com.example.carwash.service;

import com.example.carwash.dto.OrderCreateDto;
import com.example.carwash.dto.OrderDto;
import com.example.carwash.dto.OrderUpdateByUserDto;
import com.example.carwash.model.*;
import com.example.carwash.repository.BoxRepo;
import com.example.carwash.repository.OfferRepo;
import com.example.carwash.repository.OrderRepo;
import com.example.carwash.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final OfferRepo offerRepo;
    private final BoxRepo boxRepo;
    private final UserRepo userRepo;

    public OrderService(OrderRepo orderRepo, OfferRepo offerRepo, BoxRepo boxRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.offerRepo = offerRepo;
        this.boxRepo = boxRepo;
        this.userRepo = userRepo;
    }

    //TODO specifications
    public List<OrderDto> showFilteredOrders(Long boxId, LocalDateTime dateTime) {
        return orderRepo.findAll().stream().map(OrderDto::toDto).toList();
    }


    public OrderDto cancelOrder(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException(format("Заказа с id = %d нe существует", id)));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    public OrderDto create(OrderCreateDto orderCreateDto) {

        Box box = boxRepo.findById(orderCreateDto.getBoxId())
                .orElseThrow(() -> new NoSuchElementException(format("Бокса с id = %d нe существует",
                        orderCreateDto.getBoxId())));

        Offer offer = offerRepo.findById(orderCreateDto.getOfferId())
                .orElseThrow(() -> new NoSuchElementException(format("Услуги с id = %d нe существует",
                        orderCreateDto.getOfferId())));

        //TODO current user
        Order order = new Order(new User(), offer, orderCreateDto.getDateTime(), box, orderCreateDto.getPrice());
        orderRepo.save(order);
        return OrderDto.toDto(order);
    }

    public List<OrderDto> getUserOrders(User currentUser) {
        return currentUser.getOrders().stream().map(OrderDto::toDto).toList();
    }

    //TODO validation
    public OrderDto updateOrder(Long id, OrderUpdateByUserDto orderUpdateDto) {

        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException(format("Заказа с id = %d нe существует", id)));

        if (orderUpdateDto.getBoxId() != null) {
            Box box = boxRepo.findById(orderUpdateDto.getBoxId())
                    .orElseThrow(() -> new NoSuchElementException(format("Бокса с id = %d нe существует", id)));
            order.setBox(box);
        }

        if (orderUpdateDto.getStatus() != null) {
            order.setStatus(orderUpdateDto.getStatus());
        }

        if (orderUpdateDto.getDateTime() != null) {
            order.setDateTime(orderUpdateDto.getDateTime());
        }

        return OrderDto.toDto(order);
    }
}

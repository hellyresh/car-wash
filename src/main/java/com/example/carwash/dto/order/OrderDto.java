package com.example.carwash.dto.order;

import com.example.carwash.model.Order;
import com.example.carwash.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long userId;
    private Long offerId;
    private OrderStatus status;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int discount;
    private Long boxId;

    public static OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.id = order.getId();
        orderDto.userId = order.getUser().getId();
        orderDto.offerId = order.getOffer().getId();
        orderDto.status = order.getStatus();
        orderDto.date = order.getDate();
        orderDto.startTime = order.getStartTime();
        orderDto.endTime = order.getEndTime();
        orderDto.discount = order.getDiscount();
        orderDto.boxId = order.getBox().getId();
        return orderDto;
    }
}

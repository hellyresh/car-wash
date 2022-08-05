package com.example.carwash.dto.order;

import com.example.carwash.model.Order;
import com.example.carwash.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long userId;
    private Long offerId;
    private OrderStatus status;
    private Instant dateTime;
    private int discount;
    private BigDecimal price;
    private Long boxId;

    public static OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.id = order.getId();
        orderDto.userId = order.getUser().getId();
        orderDto.offerId = order.getOffer().getId();
        orderDto.status = order.getStatus();
        orderDto.dateTime = order.getDateTime();
        orderDto.discount = order.getDiscount();
        orderDto.price = order.getPrice();
        orderDto.boxId = order.getBox().getId();
        return orderDto;
    }
}

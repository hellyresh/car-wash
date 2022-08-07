package com.example.carwash.dto.orderBill;

import com.example.carwash.model.OrderBill;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderBillDto {
    private Long id;
    private Long orderId;
    private BigDecimal price;

    public static OrderBillDto toDto(OrderBill orderBill) {
        OrderBillDto orderBillDto = new OrderBillDto();
        orderBillDto.id = orderBill.getId();
        orderBillDto.orderId = orderBill.getOrder().getId();
        orderBillDto.price = orderBill.getPrice();
        return orderBillDto;
    }
}

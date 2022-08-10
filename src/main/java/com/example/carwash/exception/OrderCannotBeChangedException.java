package com.example.carwash.exception;

import com.example.carwash.model.OrderStatus;

public class OrderCannotBeChangedException extends RuntimeException {
    public OrderCannotBeChangedException(Long id, OrderStatus currentStatus, OrderStatus status) {
        super(String.format("Order with id = %d has status '%s' and it can not be changed to '%s'",
                id, currentStatus.toString(), status.toString()));
    }

    public OrderCannotBeChangedException(Long id, OrderStatus status) {
        super(String.format("Order with id = %d has status '%s' and it can not be changed",
                id, status.toString()));
    }

    public OrderCannotBeChangedException(Integer minDiscount, Integer maxDiscount) {
        super(String.format("Discount should be from %d to %d",
                minDiscount, maxDiscount));
    }
}

package com.example.carwash.dto.order;

import com.example.carwash.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
public class OrderUpdateByUserDto {
    @NotNull
    private Long id;
    private OrderStatus status;
    private Instant dateTime;
    private Long boxId;
}

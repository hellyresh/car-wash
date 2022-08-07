package com.example.carwash.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderCreateDto {
    @NotNull
    private Long offerId;
}

package com.example.carwash.dto.offer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class OfferUpdateDto {
    private String name;
    @Positive
    private Integer duration;
    @Positive
    private BigDecimal price;
}

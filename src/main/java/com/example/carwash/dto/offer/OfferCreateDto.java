package com.example.carwash.dto.offer;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class OfferCreateDto {
    @Length(min = 3, max = 30)
    private String name;
    @NotNull
    @Positive
    private Integer duration;
    @NotNull
    @Positive
    private BigDecimal price;
}

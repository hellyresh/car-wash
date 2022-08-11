package com.example.carwash.dto.offer;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class OfferUpdateDto {
    @Length(min = 3)
    private String name;
    @Positive
    private Integer duration;
    @Positive
    private BigDecimal price;
}

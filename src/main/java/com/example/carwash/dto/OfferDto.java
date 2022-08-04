package com.example.carwash.dto;

import com.example.carwash.model.Offer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
public class OfferDto {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Duration duration;
    @Positive
    private BigDecimal price;

    public static OfferDto toDto(Offer offer) {
        OfferDto offerDto = new OfferDto();
        offerDto.id = offer.getId();
        offerDto.name = offer.getName();
        offerDto.duration = offer.getDuration();
        offerDto.price = offer.getPrice();
        return offerDto;
    }
}

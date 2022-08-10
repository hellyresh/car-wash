package com.example.carwash.dto.offer;

import com.example.carwash.model.Offer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OfferDto {
    private Long id;
    private String name;

    public static OfferDto toDto(Offer offer) {
        OfferDto offerDto = new OfferDto();
        offerDto.id = offer.getId();
        offerDto.name = offer.getName();
        return offerDto;
    }
}

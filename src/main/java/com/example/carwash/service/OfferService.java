package com.example.carwash.service;

import com.example.carwash.dto.OfferCreateDto;
import com.example.carwash.dto.OfferDto;
import com.example.carwash.model.Offer;
import com.example.carwash.repository.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private final OfferRepo offerRepo;

    @Autowired
    public OfferService(OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
    }

    public OfferDto create(OfferCreateDto offerCreateDto) {
        Offer offer = new Offer(offerCreateDto.getName(), offerCreateDto.getDuration(), offerCreateDto.getPrice());
        offerRepo.save(offer);
        return OfferDto.toDto(offer);
    }

    public List<OfferDto> getOffers() {
        return offerRepo.findAll().stream().map(OfferDto::toDto).toList();
    }
}

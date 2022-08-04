package com.example.carwash.controller;

import com.example.carwash.dto.OfferCreateDto;
import com.example.carwash.dto.OfferDto;
import com.example.carwash.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/offers")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    //TODO admin
    @PostMapping
    public OfferDto create(@RequestBody OfferCreateDto offerCreateDto) {
        return offerService.create(offerCreateDto);
    }

    @GetMapping
    public List<OfferDto> getOffers() {
        return offerService.getOffers();
    }
}

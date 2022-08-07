package com.example.carwash.controller;

import com.example.carwash.dto.offer.OfferCreateDto;
import com.example.carwash.dto.offer.OfferDto;
import com.example.carwash.dto.offer.OfferUpdateDto;
import com.example.carwash.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;


    //TODO admin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDto createOffer(@Valid @RequestBody OfferCreateDto offerCreateDto) {
        return offerService.create(offerCreateDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OfferDto> getOffers() {
        return offerService.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOffer(@PathVariable Long id) {
        return offerService.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public OfferDto updateOffer(@PathVariable Long id, @Valid @RequestBody OfferUpdateDto offerUpdateDto) {
        return offerService.update(id, offerUpdateDto);
    }
}

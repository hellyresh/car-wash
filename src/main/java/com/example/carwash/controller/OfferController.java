package com.example.carwash.controller;

import com.example.carwash.dto.offer.OfferCreateDto;
import com.example.carwash.dto.offer.OfferDto;
import com.example.carwash.dto.offer.OfferUpdateDto;
import com.example.carwash.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDto createOffer(@Valid @RequestBody OfferCreateDto offerCreateDto) {
        return offerService.create(offerCreateDto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<OfferDto> getAllOffers() {
        return offerService.getOffers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public OfferDto updateOffer(@PathVariable Long id, @Valid @RequestBody OfferUpdateDto offerUpdateDto) {
        return offerService.update(id, offerUpdateDto);
    }

}

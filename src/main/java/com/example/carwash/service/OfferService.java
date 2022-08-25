package com.example.carwash.service;

import com.example.carwash.dto.offer.OfferCreateDto;
import com.example.carwash.dto.offer.OfferDto;
import com.example.carwash.dto.offer.OfferUpdateDto;
import com.example.carwash.exception.EntityNotFoundException;
import com.example.carwash.model.Offer;
import com.example.carwash.repository.OfferRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepo offerRepo;
    private final ModelMapper modelMapper;


    @Transactional
    public OfferDto create(OfferCreateDto offerCreateDto) {
        Offer offer = modelMapper.map(offerCreateDto, Offer.class);
        offerRepo.save(offer);
        return OfferDto.toDto(offer);
    }

    public List<OfferDto> getOffers() {
        return offerRepo.findAll().stream().map(OfferDto::toDto).toList();
    }

    @Transactional
    public void delete(Long id) {
        offerRepo.delete(getOffer(id));
    }

    @Transactional
    public OfferDto update(Long id, OfferUpdateDto offerUpdateDto) {
        Offer offer = getOffer(id);
        if (offerUpdateDto.getName() != null) {
            offer.setName(offerUpdateDto.getName());
        }
        if (offerUpdateDto.getDuration() != null) {
            offer.setDuration(offerUpdateDto.getDuration());
        }
        if (offerUpdateDto.getPrice() != null) {
            offer.setPrice(offerUpdateDto.getPrice());
        }
        offerRepo.save(offer);
        return OfferDto.toDto(offer);
    }

    public Offer getOffer(Long id) {
        return offerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer", "id", id.toString()));
    }

}

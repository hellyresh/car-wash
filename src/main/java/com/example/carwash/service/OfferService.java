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

    public List<OfferDto> getAll() {
        return offerRepo.findAll().stream().map(OfferDto::toDto).toList();
    }

    @Transactional
    public String delete(Long id) {
        if (!offerRepo.existsById(id)) {
            throw new EntityNotFoundException("Offer", id);
        }
        offerRepo.deleteById(id);
        //todo delete string
        return "Offer with id = " + id + " successfully deleted";
    }

    @Transactional
    public OfferDto update(Long id, OfferUpdateDto offerUpdateDto) {
        Offer offer = offerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Offer", id));
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
}

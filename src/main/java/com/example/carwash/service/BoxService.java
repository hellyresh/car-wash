package com.example.carwash.service;

import com.example.carwash.dto.BoxCreateDto;
import com.example.carwash.dto.BoxDto;
import com.example.carwash.model.Box;
import com.example.carwash.repository.BoxRepo;
import org.springframework.stereotype.Service;

@Service
public class BoxService {

    private final BoxRepo boxRepo;

    public BoxService(BoxRepo boxRepo) {
        this.boxRepo = boxRepo;
    }

    public BoxDto create(BoxCreateDto boxCreateDto) {
        Box box = new Box(boxCreateDto.getOpenTime(), boxCreateDto.getCloseTime(), boxCreateDto.getTimeCoefficient());
        boxRepo.save(box);
        return BoxDto.toDto(box);
    }
}

package com.example.carwash.service;

import com.example.carwash.dto.box.BoxCreateDto;
import com.example.carwash.dto.box.BoxDto;
import com.example.carwash.model.Box;
import com.example.carwash.repository.BoxRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoxService {

    private final BoxRepo boxRepo;

    @Transactional
    public BoxDto create(BoxCreateDto boxCreateDto) {
        Box box = new Box(boxCreateDto.getOpenTime(), boxCreateDto.getCloseTime(), boxCreateDto.getTimeCoefficient());
        boxRepo.save(box);
        return BoxDto.toDto(box);
    }

}

package com.example.carwash.service;

import com.example.carwash.dto.box.BoxCreateDto;
import com.example.carwash.dto.box.BoxDto;
import com.example.carwash.dto.box.BoxUpdateDto;
import com.example.carwash.exception.EntityNotFoundException;
import com.example.carwash.model.Box;
import com.example.carwash.model.Offer;
import com.example.carwash.repository.BoxRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

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


    public List<BoxDto> getBoxes() {
        return boxRepo.findAll().stream().map(BoxDto::toDto).toList();
    }

    public Box getBox(Long id) {
        return boxRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Box", "id", id.toString()));
    }

    public Box getAvailableBox(Offer offer, LocalDateTime dateTime) {
        return boxRepo.findBestBox(dateTime, offer.getDuration());
    }

    @Transactional
    public BoxDto update(BoxUpdateDto boxUpdateDto, Long id) {
        Box box = getBox(id);
        if (boxUpdateDto.getOpenTime() != null) {
            box.setOpenTime(boxUpdateDto.getOpenTime());
        }
        if (boxUpdateDto.getCloseTime() != null) {
            box.setCloseTime(boxUpdateDto.getCloseTime());
        }
        if (boxUpdateDto.getTimeCoefficient() != null) {
            box.setTimeCoefficient(boxUpdateDto.getTimeCoefficient());
        }
        boxRepo.save(box);
        return BoxDto.toDto(box);
    }

    @Transactional
    public void delete(Long id) {
        boxRepo.delete(getBox(id));
    }
}

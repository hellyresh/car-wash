package com.example.carwash.dto;

import com.example.carwash.model.Box;
import lombok.Data;

import java.time.LocalTime;

@Data
public class BoxDto {
    private Long id;
    private LocalTime openTime;
    private LocalTime closeTime;
    private double timeCoefficient;

    public static BoxDto toDto(Box box) {
        BoxDto boxDto = new BoxDto();
        boxDto.id = box.getId();
        boxDto.openTime = box.getOpenTime();
        boxDto.closeTime = box.getCloseTime();
        boxDto.timeCoefficient = boxDto.getId();
        return boxDto;
    }

}

package com.example.carwash.dto.box;

import com.example.carwash.model.Box;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class BoxDto {
    private Long id;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Double timeCoefficient;

    public static BoxDto toDto(Box box) {
        BoxDto boxDto = new BoxDto();
        boxDto.id = box.getId();
        boxDto.openTime = box.getOpenTime();
        boxDto.closeTime = box.getCloseTime();
        boxDto.timeCoefficient = box.getTimeCoefficient();
        return boxDto;
    }

}

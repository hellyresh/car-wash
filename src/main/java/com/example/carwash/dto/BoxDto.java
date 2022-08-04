package com.example.carwash.dto;

import com.example.carwash.model.Box;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalTime;

@Getter
@Setter
public class BoxDto {
    @NotNull
    private Long id;
    @NotNull
    private LocalTime openTime;
    @NotNull
    private LocalTime closeTime;
    @Positive
    private double timeCoefficient;

    public static BoxDto toDto(Box box) {
        BoxDto boxDto = new BoxDto();
        boxDto.id = box.getId();
        boxDto.openTime = box.getOpenTime();
        boxDto.closeTime = box.getCloseTime();
        boxDto.timeCoefficient = boxDto.getTimeCoefficient();
        return boxDto;
    }

}

package com.example.carwash.dto.operator;

import com.example.carwash.model.Operator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperatorDto {
    private Long id;
    private Integer minDiscount;
    private Integer maxDiscount;
    private Long boxId;
    private Long userId;

    public static OperatorDto toDto(Operator operator) {
        OperatorDto operatorDto = new OperatorDto();
        operatorDto.setId(operator.getId());
        operatorDto.setMinDiscount(operator.getMinDiscount());
        operatorDto.setMaxDiscount(operator.getMaxDiscount());
        if (operator.getBox() != null) {
            operatorDto.setBoxId(operator.getBox().getId());
        }
        operatorDto.setUserId(operator.getUser().getId());
        return operatorDto;
    }
}

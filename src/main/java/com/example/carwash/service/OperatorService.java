package com.example.carwash.service;

import com.example.carwash.dto.operator.DiscountDto;
import com.example.carwash.dto.operator.OperatorDto;
import com.example.carwash.exception.BoxAlreadyHasOperatorException;
import com.example.carwash.exception.EntityNotFoundException;
import com.example.carwash.model.Operator;
import com.example.carwash.model.User;
import com.example.carwash.repository.OperatorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorRepo operatorRepo;
    private final BoxService boxService;

    public Operator getOperatorByUserId(Long userId) {
        return (Operator) operatorRepo.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Operator", "userId", userId.toString()));
    }

    public List<OperatorDto> getOperators() {
        return operatorRepo.findAll().stream().map(OperatorDto::toDto).toList();
    }

    @Transactional
    public OperatorDto setOperatorsDiscount(Long operatorId, DiscountDto discountDto) {
        Operator operator = operatorRepo.findById(operatorId)
                .orElseThrow(() -> new EntityNotFoundException("Operator", "id", operatorId.toString()));
        if (discountDto.getMinDiscount() > discountDto.getMaxDiscount()) {
            throw new IllegalArgumentException("Min discount shouldn't be greater than max discount");
        }
        operator.setMinDiscount(discountDto.getMinDiscount());
        operator.setMaxDiscount(discountDto.getMaxDiscount());
        return OperatorDto.toDto(operator);
    }

    public void create(User user) {
        Operator operator = new Operator();
        operator.setUser(user);
        operatorRepo.save(operator);
    }

    public Operator getOperator(Long id) {
        return operatorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Operator", "id", id.toString()));
    }

    @Transactional
    public OperatorDto setOperatorsBox(Long id, Long boxId) {
        if (operatorRepo.existsByBoxId(boxId)) {
            throw new BoxAlreadyHasOperatorException(boxId);
        }
        Operator operator = getOperator(id);
        operator.setBox(boxService.getBox(boxId));
        operatorRepo.save(operator);
        return OperatorDto.toDto(operator);
    }

    @Transactional
    public void delete(Long id) {
        operatorRepo.delete(getOperator(id));
    }
}

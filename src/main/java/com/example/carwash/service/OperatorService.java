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

    public Operator getOperatorByUser(User user) {
        return operatorRepo.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Operator", "userId", user.getId().toString()));
    }

    public List<OperatorDto> getOperators() {
        return operatorRepo.findAll().stream().map(OperatorDto::toDto).toList();
    }

    @Transactional
    public OperatorDto setOperatorDiscount(Long operatorId, DiscountDto discountDto) {
        Operator operator = getOperator(operatorId);
        if (discountDto.getMinDiscount() > discountDto.getMaxDiscount()) {
            throw new IllegalArgumentException("Min discount shouldn't be greater than max discount");
        }
        operator.setMinDiscount(discountDto.getMinDiscount());
        operator.setMaxDiscount(discountDto.getMaxDiscount());
        operatorRepo.save(operator);
        return OperatorDto.toDto(operator);
    }

    @Transactional
    public Operator create(User user) {
        Operator operator = new Operator();
        operator.setUser(user);
        operatorRepo.save(operator);
        return operator;
    }

    public Operator getOperator(Long id) {
        return operatorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Operator", "id", id.toString()));
    }

    @Transactional
    public OperatorDto setOperatorBox(Long id, Long boxId) {
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

    @Transactional
    public OperatorDto deleteOperatorBox(Long id) {
        Operator operator = getOperator(id);
        operator.setBox(null);
        operatorRepo.save(operator);
        return OperatorDto.toDto(operator);
    }

}

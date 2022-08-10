package com.example.carwash.controller;

import com.example.carwash.dto.operator.DiscountDto;
import com.example.carwash.dto.operator.OperatorDto;
import com.example.carwash.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/operators")
@RequiredArgsConstructor
public class OperatorController {
    private final OperatorService operatorService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OperatorDto> getAllOperators() {
        return operatorService.getOperators();
    }

    @PutMapping("{id}/assign-discount-interval")
    @ResponseStatus(HttpStatus.OK)
    public OperatorDto setDiscountToOperator(@PathVariable Long id, @Valid @RequestBody DiscountDto discountDto) {
        return operatorService.setOperatorsDiscount(id, discountDto);
    }

    @PutMapping("{id}/assign-box/{boxId}")
    @ResponseStatus(HttpStatus.OK)
    public OperatorDto setBoxToOperator(@PathVariable Long id, @PathVariable Long boxId) {
        return operatorService.setOperatorsBox(id, boxId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOperator(@PathVariable Long id) {
        operatorService.delete(id);
    }
}

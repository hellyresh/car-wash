package com.example.carwash.controller;

import com.example.carwash.dto.operator.DiscountDto;
import com.example.carwash.dto.operator.OperatorDto;
import com.example.carwash.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("api/operators")
@RequiredArgsConstructor
public class OperatorController {
    private final OperatorService operatorService;

    @GetMapping
    public List<OperatorDto> getAllOperators() {
        return operatorService.getOperators();
    }

    @PutMapping("{id}/discount")
    public OperatorDto setDiscountToOperator(@PathVariable Long id, @Valid @RequestBody DiscountDto discountDto) {
        return operatorService.setOperatorDiscount(id, discountDto);
    }

    @PutMapping("{id}/box/{boxId}")
    public OperatorDto setBoxToOperator(@PathVariable Long id, @PathVariable Long boxId) {
        return operatorService.setOperatorBox(id, boxId);
    }

    @DeleteMapping("{id}/box")
    public OperatorDto deleteBoxFromOperator(@PathVariable Long id) {
        return operatorService.deleteOperatorBox(id);
    }

    @DeleteMapping("{id}")
    public void deleteOperator(@PathVariable Long id) {
        operatorService.delete(id);
    }

}

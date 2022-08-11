package com.example.carwash.controller;

import com.example.carwash.dto.box.BoxCreateDto;
import com.example.carwash.dto.box.BoxDto;
import com.example.carwash.dto.box.BoxUpdateDto;
import com.example.carwash.service.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("api/boxes")
public class BoxController {

    private final BoxService boxService;


    @PostMapping
    public BoxDto create(@Valid @RequestBody BoxCreateDto boxCreateDto) {
        return boxService.create(boxCreateDto);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BoxDto> getBoxes() {
        return boxService.getBoxes();
    }

    @PutMapping("{id}")
    public BoxDto update(@Valid @RequestBody BoxUpdateDto boxUpdateDto, @PathVariable Long id) {
        return boxService.update(boxUpdateDto, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        boxService.delete(id);
    }


}

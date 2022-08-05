package com.example.carwash.controller;

import com.example.carwash.dto.box.BoxCreateDto;
import com.example.carwash.dto.box.BoxDto;
import com.example.carwash.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/boxes")
public class BoxController {

    private final BoxService boxService;

    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }


    //TODO admin
    @PostMapping
    public BoxDto create(@RequestBody BoxCreateDto boxCreateDto) {
        return boxService.create(boxCreateDto);
    }
}

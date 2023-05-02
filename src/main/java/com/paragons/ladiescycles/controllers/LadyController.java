package com.paragons.ladiescycles.controllers;

import com.paragons.ladiescycles.dtos.requestDto.CreateLadyRequest;
import com.paragons.ladiescycles.dtos.responseDto.RegisterLadyResponse;
import com.paragons.ladiescycles.services.LadyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
public class LadyController {
    private final LadyService ladyService;

    @Autowired
    public LadyController(LadyService ladyService) {
        this.ladyService = ladyService;
    }


    @PostMapping("lady/create")
    public ResponseEntity<RegisterLadyResponse> createLady(@RequestBody CreateLadyRequest createLady){
        return new ResponseEntity<>(ladyService.createLady(createLady), HttpStatus.CREATED);
    }
}

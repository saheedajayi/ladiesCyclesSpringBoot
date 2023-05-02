package com.paragons.ladiescycles.controllers;


import com.paragons.ladiescycles.data.models.Cycles;
import com.paragons.ladiescycles.services.CyclesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("")
public class CycleController {
    private final CyclesService cyclesService;

    @GetMapping("cycles/{id}")
    public ResponseEntity<Cycles> getCycles(@PathVariable Long id){
        return new ResponseEntity<>(cyclesService.getOneCycle(id), HttpStatus.OK);
    }

    @GetMapping("cycles12/{id}")
    public ResponseEntity<?> get12Cycles(@PathVariable Long id){
        return new ResponseEntity<>(cyclesService.get12Cycles(id), HttpStatus.OK);
    }

}

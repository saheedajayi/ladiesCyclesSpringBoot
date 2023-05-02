package com.paragons.ladiescycles.services;

import com.paragons.ladiescycles.dtos.requestDto.CreateLadyRequest;
import com.paragons.ladiescycles.dtos.requestDto.RegisterRequest;
import com.paragons.ladiescycles.dtos.responseDto.RegisterLadyResponse;


public interface LadyService {
    RegisterLadyResponse createLady(CreateLadyRequest ladyRequest);
    RegisterLadyResponse registerLady(RegisterRequest registerRequest);
}

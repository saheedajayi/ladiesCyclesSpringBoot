package com.paragons.ladiescycles.dtos.responseDto;

import lombok.Data;
import lombok.NonNull;


@Data
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

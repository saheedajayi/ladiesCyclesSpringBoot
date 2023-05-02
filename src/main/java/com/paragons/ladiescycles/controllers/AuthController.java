package com.paragons.ladiescycles.controllers;

import com.paragons.ladiescycles.data.models.Lady;
import com.paragons.ladiescycles.data.models.Role;
import com.paragons.ladiescycles.data.repositories.LadyRepository;
import com.paragons.ladiescycles.data.repositories.RoleRepository;
import com.paragons.ladiescycles.dtos.requestDto.LoginRequest;
import com.paragons.ladiescycles.dtos.requestDto.RegisterRequest;
import com.paragons.ladiescycles.dtos.responseDto.AuthResponse;
import com.paragons.ladiescycles.dtos.responseDto.RegisterLadyResponse;
import com.paragons.ladiescycles.security.JwtGenerator;
import com.paragons.ladiescycles.services.LadyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private JwtGenerator jwtGenerator;
    private final LadyService ladyService;

    @PostMapping("register")
    public ResponseEntity<RegisterLadyResponse> register(@RequestBody RegisterRequest register){
        return new ResponseEntity<>(ladyService.registerLady(register), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest login){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }


}

package com.paragons.ladiescycles.services;

import com.paragons.ladiescycles.data.models.Lady;
import com.paragons.ladiescycles.data.models.Role;
import com.paragons.ladiescycles.data.repositories.LadyRepository;
import com.paragons.ladiescycles.data.repositories.RoleRepository;
import com.paragons.ladiescycles.dtos.requestDto.CreateLadyRequest;
import com.paragons.ladiescycles.dtos.requestDto.RegisterRequest;
import com.paragons.ladiescycles.dtos.responseDto.RegisterLadyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class LadyServiceImpl implements LadyService {
    private final LadyRepository ladyRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public LadyServiceImpl(
            LadyRepository ladyRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.ladyRepository = ladyRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public RegisterLadyResponse createLady(CreateLadyRequest ladyRequest) {
        Lady lady = new Lady();
        lady.setUsername(ladyRequest.getUsername());
        lady.setFlowEndDate(ladyRequest.getFlowEndDate());
        lady.setCycleStartDate(ladyRequest.getCycleStartDate());
        lady.setCycleEndDate(ladyRequest.getCycleEndDate());

        Lady newLady = ladyRepository.save(lady);

        return new RegisterLadyResponse(newLady);

    }

    @Override
    public RegisterLadyResponse registerLady(RegisterRequest register) {
        if(ladyRepository.existsByUsername(register.getUsername())){
            return new RegisterLadyResponse(null, "Username is already taken");
        }

//        Role ladyRole = Role.builder()
//                .name("USER")
//                .build();
//
//        roleRepository.save(ladyRole);

        Lady lady = Lady.builder()
                .username(register.getUsername())
                .password(passwordEncoder.encode(register.getPassword()))
                .cycleStartDate(register.getCycleStartDate())
                .flowEndDate(register.getFlowEndDate())
                .cycleEndDate(register.getCycleEndDate())
                .build();

        Optional<Role> optionalRole = roleRepository.findByName("USER");
        Role role = null;
        if(optionalRole.isPresent()){
            role = optionalRole.get();
        }
        lady.setRole(Collections.singletonList(role));
        Lady savedLady = ladyRepository.save(lady);

        return new RegisterLadyResponse(savedLady);
    }
}

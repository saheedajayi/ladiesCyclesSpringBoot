package com.paragons.ladiescycles.dtos.requestDto;

import com.paragons.ladiescycles.data.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String cycleStartDate;
    private String flowEndDate;
    private String cycleEndDate;
    private String password;
}

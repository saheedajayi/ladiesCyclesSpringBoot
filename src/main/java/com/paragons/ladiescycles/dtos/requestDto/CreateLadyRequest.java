package com.paragons.ladiescycles.dtos.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLadyRequest {
    private String username;
//    private String flowStartDate;
    private String cycleStartDate;
    private String flowEndDate;
    private String cycleEndDate;
    private String password;
}

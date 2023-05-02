package com.paragons.ladiescycles.dtos.responseDto;


import com.paragons.ladiescycles.data.models.Lady;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterLadyResponse {
    private Long id;
    private String welcomeMessage;

    public RegisterLadyResponse(Lady lady) {
        this.id = lady.getId();
        this.welcomeMessage = "Registration Successful";
    }
}

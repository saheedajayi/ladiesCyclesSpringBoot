package com.paragons.ladiescycles.dtos.responseDto;


import com.paragons.ladiescycles.data.models.Lady;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateLadyResponse {
    private Long id;
    private String welcomeMessage;

    public CreateLadyResponse(Lady lady) {
        this.id = lady.getId();
        this.welcomeMessage = String.format("Welcome %s", lady.getUsername());
    }
}

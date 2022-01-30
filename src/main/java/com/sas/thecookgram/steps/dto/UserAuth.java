package com.sas.thecookgram.steps.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAuth {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

}

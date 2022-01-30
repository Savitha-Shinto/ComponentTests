package com.sas.thecookgram.steps.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeUserDto {
    private long id;
    private String name;
    private String emailId;
    private String password;
    private String confirmpassword;
    private String town;
    private String county;
    private String country;
    private String userImage;
    private String token;
}
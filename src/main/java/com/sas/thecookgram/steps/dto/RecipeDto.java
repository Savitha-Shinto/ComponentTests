package com.sas.thecookgram.steps.dto;


import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeDto {
    private long id;
    private String title;
    private String description;
    private long userId;
    private String recipeImage;
    private String ingredients;
    private String allergens;
    private String preparation;
    private String cuisine;
    private String serves;
    private String preparationTime;
    private String cookingTime;
    private String videoLink;


}
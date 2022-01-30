package com.sas.thecookgram.steps.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookmarkDto {
    private long id;
    private long recipeId;
    private long userId;
    private long friendId;
    private String searchString;
}
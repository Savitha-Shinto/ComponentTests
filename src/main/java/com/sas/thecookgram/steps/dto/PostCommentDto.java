package com.sas.thecookgram.steps.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostCommentDto {
    private long id;
    private int userId;
    private int postId;
    private String comment;


}
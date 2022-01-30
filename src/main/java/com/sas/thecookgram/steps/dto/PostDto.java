package com.sas.thecookgram.steps.dto;


import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {
    private long id;
    private long userId;
    private String cuisine;
    private String title;
    private String desc;
    private String postImage;
    private String videoLink;
    private int totallikecountperpost;
    private String days;
}
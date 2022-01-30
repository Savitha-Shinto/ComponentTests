package com.sas.thecookgram.steps.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FanFollowerDto {
    private long id;
     private long userId;
    private long friendId;
    private String search;
}
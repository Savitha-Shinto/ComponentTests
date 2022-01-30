package com.sas.thecookgram.steps.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordResetTokenDto {
    private long id;
    private long userId;
    private String token;
    private Date expiryDate;
    private String enabled;
    private Timestamp createdTimeStamp;
    private Timestamp updatedTimeStamp;
}
package com.sas.thecookgram.steps.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HomeFoodExcpetionResponse {
    private long reasonCode;
    private String resonDesc;

    public HomeFoodExcpetionResponse(HomeFoodErrors homeFoodErrors) {
         setReasonCode(homeFoodErrors.getErrorCode());
         setResonDesc(homeFoodErrors.getErrorMessage());
    }

    public long getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(long reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getResonDesc() {
        return resonDesc;
    }

    public void setResonDesc(String resonDesc) {
        this.resonDesc = resonDesc;
    }
}

package com.sas.thecookgram.steps;


import com.sas.thecookgram.steps.dto.PayloadContext;

public abstract class AbstractSteps {
  public PayloadContext payloadContext() {
    return PayloadContext.CONTEXT;
  }
}

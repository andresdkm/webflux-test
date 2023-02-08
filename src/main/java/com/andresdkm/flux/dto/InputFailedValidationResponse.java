package com.andresdkm.flux.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InputFailedValidationResponse {

    private Integer errorCode;

    private Integer input;

    private String message;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getInput() {
        return input;
    }

    public void setInput(Integer input) {
        this.input = input;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

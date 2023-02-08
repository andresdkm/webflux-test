package com.andresdkm.flux.exception;

public class InputValidationException extends RuntimeException{

    public static final String MSG = "aLLOWED RANGE IS 10 -20";
    public static final Integer errorCode = 100;
    private Integer input;

    public InputValidationException(Integer input){
        super(MSG);
        this.input = input;
    }


    public Integer getInput() {
        return input;
    }
}

package com.bac.quizonline.model.service.exception;

public class DoubleEmailException extends Exception {
    @Override
    public String getMessage() {
        return "This email is existed!";
    }
}

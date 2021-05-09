package com.bac.quizonline.model.service.exception;

public class NotFoundUserException extends Exception{
    @Override
    public String getMessage() {
        return "Invalid email or password";
    }
}

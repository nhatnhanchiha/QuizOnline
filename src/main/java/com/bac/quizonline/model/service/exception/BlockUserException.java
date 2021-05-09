package com.bac.quizonline.model.service.exception;

public class BlockUserException extends Exception {
    @Override
    public String getMessage() {
        return "This user is blocked!";
    }
}

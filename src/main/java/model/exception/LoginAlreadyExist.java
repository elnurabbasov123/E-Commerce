package model.exception;

import model.enums.Exceptions;

public class LoginAlreadyExist extends RuntimeException{
    public LoginAlreadyExist(Exceptions exception,String val) {
        super(String.format(exception.getMessage(),val));
    }
}

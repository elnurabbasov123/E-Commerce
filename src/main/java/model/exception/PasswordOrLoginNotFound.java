package model.exception;

import model.enums.Exceptions;

public class PasswordOrLoginNotFound extends RuntimeException{
    public PasswordOrLoginNotFound(Exceptions ex, String val) {
        super(String.format(ex.getMessage(),val));
    }
}

package model.exception;

import model.enums.Exceptions;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(Exceptions exceptions,String name,String surname) {
        super(String.format(exceptions.getMessage(),name,surname));
    }
}

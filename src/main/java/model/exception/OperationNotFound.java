package model.exception;

import model.enums.Exceptions;

public class OperationNotFound extends RuntimeException{
    public OperationNotFound(Exceptions exceptions) {
        super(exceptions.getMessage());
    }
}

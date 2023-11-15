package model.exception;

import model.enums.Exceptions;

public class ObjectDontUpdated extends RuntimeException{
    public ObjectDontUpdated(Exceptions exceptions,String message) {
        super(String.format(exceptions.getMessage(),message));
    }
}

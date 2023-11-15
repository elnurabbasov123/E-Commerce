package model.exception;

import model.enums.Exceptions;

public class ObjectDontAddException extends RuntimeException{
    public ObjectDontAddException(Exceptions ex, String val) {
        super(String.format(ex.getMessage(),val));
    }
}

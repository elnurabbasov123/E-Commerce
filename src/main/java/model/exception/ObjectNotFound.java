package model.exception;

import model.enums.Exceptions;

public class ObjectNotFound extends RuntimeException{
    public ObjectNotFound(String s1, String s2,Exceptions exception) {
        super(String.format(exception.getMessage(),s1,s2));
    }
}

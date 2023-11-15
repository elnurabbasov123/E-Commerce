package model.exception;

import model.enums.Exceptions;

public class OrderDontAdoptedException extends RuntimeException{
    public OrderDontAdoptedException(String name, String surname, Exceptions exceptions) {
        super(String.format(exceptions.getMessage(),name,surname));
    }
}

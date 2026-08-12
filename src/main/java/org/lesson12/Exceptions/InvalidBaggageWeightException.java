package org.lesson12.Exceptions;

public class InvalidBaggageWeightException extends RuntimeException {
    //вес багажа меньше или равен нулю
    public InvalidBaggageWeightException(String message) {
        super(message);
    }
}

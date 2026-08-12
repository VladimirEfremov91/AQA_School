package org.lesson12.Exceptions;

public class OverweightBaggageException extends AirportServiceException {
    //Багаж слишком тяжелый
    public OverweightBaggageException(String message) {
        super(message);
    }
}

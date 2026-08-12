package org.lesson12.Exceptions;

public class BaggageTagPrintException extends AirportServiceException {
    // Не получилось напечатать багажную бирку
    public BaggageTagPrintException(String message) {
        super(message);
    }
}

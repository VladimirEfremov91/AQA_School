package org.lesson12.Exceptions;

public class FlightNotFoundException extends AirportServiceException {
    // Указанного рейса нет в списке доступных рейсов
    public FlightNotFoundException(String message) {
        super(message);
    }
}

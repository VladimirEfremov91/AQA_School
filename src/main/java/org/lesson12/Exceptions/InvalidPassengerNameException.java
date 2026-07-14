package org.lesson12.Exceptions;

public class InvalidPassengerNameException extends RuntimeException {
    // имя пассажира null или пустое
    public InvalidPassengerNameException(String message) {
        super(message);
    }
}
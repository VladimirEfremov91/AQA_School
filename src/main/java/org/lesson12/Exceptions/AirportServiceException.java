package org.lesson12.Exceptions;

public class AirportServiceException extends Exception {
    //Базовое проверяемое исключение
    public AirportServiceException(String message) {
        super(message);
    }
}
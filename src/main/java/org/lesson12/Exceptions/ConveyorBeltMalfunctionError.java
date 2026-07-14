package org.lesson12.Exceptions;

public class ConveyorBeltMalfunctionError extends Error {
    //критическая ситуация, блокирующая работу пункта приема багажа.
    public ConveyorBeltMalfunctionError(String message) {
        super(message);
    }
}

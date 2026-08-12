package org.lesson12;

import org.lesson12.Exceptions.*;

public class BaggageDropDesk {
    private final String[] availableFlights;
    private static final int BAGGAGE_FARE = 332;

    public BaggageDropDesk(String[] availableFlights) {
        this.availableFlights = availableFlights;
    }

    //Метод сдачи багажа
    public BaggageTicket baggageCheckIn(String passengerName, String flightNumber, int baggageWeight) throws AirportServiceException {
        if (flightNumber == null || flightNumber.equals("")) {
            throw new FlightNotFoundException("Проверь ввод номера рейса");
        }
        boolean foundFlight = false;
        for (String flight : availableFlights) {
            if (flight.equalsIgnoreCase(flightNumber.trim())) {
                foundFlight = true;
                break;
            }
        }
        if (!foundFlight) {
            throw new FlightNotFoundException("Такого рейса не существует в данном аэропорте");
        }
        if (passengerName == null || passengerName.equals("")) {
            throw new InvalidPassengerNameException("Имя пассажира не должно быть пустым");
        }
        if (baggageWeight <= 0) {
            throw new InvalidBaggageWeightException("Вес багажа не может быть меньше или равен нуля");
        }
        if (baggageWeight > 23) {
            throw new OverweightBaggageException("Ваш багаж слишком тяжел для этого самолета");
        }
        if (passengerName.equals("Мисс Бурпл")) {
            throw new BaggageTagPrintException("У нас тут случайно всё сломалось, какая неожиданность!");
        }
        int baggageTax = baggageWeight * BAGGAGE_FARE;
        System.out.println("Уважаемый " + passengerName + ", Вам начислен налог за выдачу бирки в размере: " + baggageTax + " руб.");
        return new BaggageTicket(passengerName, flightNumber, baggageWeight);
    }

}

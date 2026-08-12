package org.lesson12;

import org.lesson12.Exceptions.*;

public class Main {
    public static void main(String[] args) {
        String[] flights = {"SU-123", "TK-777", "KC-909", "AE-404"};
        BaggageDropDesk baggageDropDesk = new BaggageDropDesk(flights);

        // успешная сдача багажа
        checkScenario(baggageDropDesk, "Иван Иванов", "TK-777", 19);

        // рейса не существует
        checkScenario(baggageDropDesk, "Иван Безрейсов", "TK-0777", 20);

        // багаж слишком тяжелый
        checkScenario(baggageDropDesk, "Иван Тяжелый", "TK-777", 24);

        // проблема с печатью бирки
        checkScenario(baggageDropDesk, "Мисс Бурпл", "AE-404", 20);

        // некорректное имя пассажира
        checkScenario(baggageDropDesk, null, "TK-777", 20);

        // некорректный вес багажа
        checkScenario(baggageDropDesk, "Иван Отрицательный", "TK-777", -20);
    }

    private static void checkScenario(BaggageDropDesk baggageDropDesk,
                                      String passengerName,
                                      String flightNumber,
                                      int baggageWeight) {
        try {
            BaggageTicket ticket = baggageDropDesk.baggageCheckIn(passengerName, flightNumber, baggageWeight);
            System.out.println("Багаж успешно сдан. Багажная бирка: " + ticket);
        } catch (FlightNotFoundException e) {
            System.out.println("Ошибка требуемого рейса: " + e.getMessage());
        } catch (InvalidPassengerNameException e) {
            System.out.println("Ошибка имени пассажира: " + e.getMessage());
        } catch (InvalidBaggageWeightException e) {
            System.out.println("Ошибка взвешивания багажа: " + e.getMessage());
        } catch (OverweightBaggageException e) {
            System.out.println("Ошибка перевеса багажа: " + e.getMessage());
        } catch (BaggageTagPrintException e) {
            System.out.println("Ошибка принтера: " + e.getMessage());
        } catch (AirportServiceException e) {
            System.out.println("Общая ошибка сервиса: " + e.getMessage());
        }
        System.out.println("------------------------------");
    }
}
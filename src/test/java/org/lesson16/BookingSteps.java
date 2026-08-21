package org.lesson16;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import java.util.List;
import java.util.Map;

public class BookingSteps {

    @Дано("в ресторане есть свободный столик на {int} гостей")
    public void restaurantHasAvailableTable(int guestCount) {
        System.out.println("В ресторане есть свободный столик на " + guestCount + " гостей");
    }

    @Дано("в ресторане есть свободные столики на {int}, {int}, {int} гостей")
    public void restaurantHasAvailableTables(int firstTableCapacity, int secondTableCapacity, int thirdTableCapacity) {
        System.out.println("В ресторане есть свободные столики вместимостью: " + firstTableCapacity + ", "
                        + secondTableCapacity + ", " + thirdTableCapacity + " гостей"
        );
    }

    @Когда("гость бронирует столик на {int} гостей в {int} часов без пожеланий")
    public void guestBooksTableWithoutRequests(int guestCount, int bookingHour) {
        System.out.println("Гость бронирует столик на " + guestCount + " гостей в " + bookingHour + " часов без пожеланий");
    }

    @Когда("гость бронирует столик на {int} гостей в {int} часов с пожеланием:")
    public void guestBooksTableWithRequests(int guestCount, int bookingHour, String request) {
        System.out.println("Гость бронирует столик на " + guestCount + " гостей в " + bookingHour
                + " пожеланием " + request);
    }

    @Тогда("бронирование успешно создано")
    public void bookingSuccessfullyCreated() {
        System.out.println("Бронирование успешно создано");
    }

    @Дано("в ресторане нет свободного столика на {int} гостей")
    public void restaurantHasNoAvailableTable(int guestCount) {
        System.out.println("В ресторане нет свободного столика на " + guestCount + " гостей");
    }

    @Тогда("гостю отказано в бронировании")
    public void bookingRejected() {
        System.out.println("Гостю отказано в бронировании");
    }

    @Дано("у гостя есть активное бронирование")
    public void guestHasActiveBooking() {
        System.out.println("У гостя есть активное бронирование");
    }

    @Когда("гость отменяет бронирование")
    public void guestCancelsBooking() {
        System.out.println("Гость отменяет бронирование");
    }

    @Тогда("бронирование отменено")
    public void bookingCancelled() {
        System.out.println("Бронирование отменено");
    }

    @Тогда("результат бронирования - {word}")
    public void bookingResult(String bookingResult) {
        System.out.println("Результат бронирования: " + bookingResult);
    }

    @Дано("в ресторане есть столики:")
    public void restaurantHasTables(DataTable table) {
        List<Map<String, String>> tables = table.asMaps(String.class, String.class);
        for (Map<String, String> tableData : tables) {
            String tableNumber = tableData.get("номер");
            String capacity = tableData.get("вместимость");
            System.out.println("Столик №" + tableNumber+ ", вместимость: "+ capacity+ " гостей");
        }
    }
}
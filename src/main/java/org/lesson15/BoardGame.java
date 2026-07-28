package org.lesson15;

import java.util.Objects;

public class BoardGame {
    private String title;
    private int minAge;
    private int oneDayPrice;
    private boolean isRent;

    public BoardGame(String title, int minAge, int oneDayPrice) {
        if (title == null || title.length() == 0) {
            throw new IllegalArgumentException("Название не может быть null или пустым");
        }
        if (minAge < 0) {
            throw new IllegalArgumentException("Минимальный возраст не может быть менее 0");
        }
        if (oneDayPrice < 0) {
            throw new IllegalArgumentException("Минимальная цена за день не может быть менее 0");
        }
        this.title = title;
        this.minAge = minAge;
        this.oneDayPrice = oneDayPrice;
        this.isRent = false;
    }

    public String getTitle() {
        return title;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getOneDayPrice() {
        return oneDayPrice;
    }

    public boolean isRent() {
        return isRent;
    }

    public boolean canBeRentedBy(int age) {
        return age >= minAge;
    }

    public void setRent(boolean rent) {
        isRent = rent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BoardGame boardGame = (BoardGame) o;
        return minAge == boardGame.minAge && oneDayPrice == boardGame.oneDayPrice && Objects.equals(title, boardGame.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, minAge, oneDayPrice);
    }
}

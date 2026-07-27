package org.lesson15.TASK15;

public class BoardGame {
    private String title;
    private int minAge;
    private double oneDayPrice;
    private boolean isRent;

    public BoardGame(String title, int minAge, double oneDayPrice) {
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

    public double getOneDayPrice() {
        return oneDayPrice;
    }

    public boolean isRent() {
        return isRent;
    }

    public boolean canBeRentedBy(int age) {
        return age >= minAge;
    }
}

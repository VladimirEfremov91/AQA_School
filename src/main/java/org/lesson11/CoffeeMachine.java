package org.lesson11;

public class CoffeeMachine {
    private static final int CRITICAL_WATER_AMOUNT = 200;

    public void makeCoffee(int waterAmount) {
        if (waterAmount <= CRITICAL_WATER_AMOUNT) {
            String error = "Невозможно приготовить кофе. Недостаточно воды: "
                    + waterAmount + " мл. Минимально необходимо больше "
                    + CRITICAL_WATER_AMOUNT + " мл";
            throw new NotEnoughWaterException(error);
        } else {
            System.out.println("Кофе приготовлен");
        }
    }

    public void calculateCups(int waterAmount, int cupVolume) {
        int cups = waterAmount / cupVolume;
        System.out.println(cups + " чашек кофе объемом " + cupVolume
                + " мл можно приготовить из " + waterAmount + " мл воды");
    }

    public void printCoffeeName(String coffeeName) {
        System.out.println(coffeeName.toUpperCase());
    }
}
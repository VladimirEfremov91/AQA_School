package org.lesson11;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

// 3.1 Проверить InputMismatchException
        try {
            System.out.println("Введите количество воды:");
            int waterAmount = scanner.nextInt();
            System.out.println("Вы ввели: " + waterAmount + " мл");
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: нужно было ввести число");
        }

// 3.2 Проверить своё исключение
        try {
            coffeeMachine.makeCoffee(201);
            coffeeMachine.makeCoffee(100);
        } catch (NotEnoughWaterException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Проверка кофемашины завершена");
        }

// 3.3 Проверить ArithmeticException
        try {
            coffeeMachine.calculateCups(333, 45);
            coffeeMachine.calculateCups(1000, 0);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: размер чашки не может быть 0");
        }

// 3.4 Проверить NullPointerException
        try {
            String coffeeName1 = "Nescafe";
            String coffeeName2 = null;
            coffeeMachine.printCoffeeName(coffeeName1);
            coffeeMachine.printCoffeeName(coffeeName2);
        } catch (NullPointerException e) {
            System.out.println("Ошибка: название кофе отсутствует");
        }

        scanner.close();
    }
}
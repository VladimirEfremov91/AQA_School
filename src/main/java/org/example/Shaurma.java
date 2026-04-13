package org.example;
public class Shaurma {
    public static void main(String[] args) {
        String employeeName = "Вася";
        String employeePosition = "Бариста";
        int shiftRate = 500;
        int shiftCount = 18;
        int bonus = 10000;
        int burnedLavashPenalty = 50;
        int shawarmaPrice = 222;
        int soldShawarmaCount = 1234;

        int baseSalary = shiftRate * shiftCount;
        int totalSalary = baseSalary + bonus - burnedLavashPenalty;
        int shawarmaIncome = shawarmaPrice * soldShawarmaCount;

        System.out.println("Сотрудник: " + employeeName);
        System.out.println("Должность: " + employeePosition);
        System.out.println("Оплата за смены: " + baseSalary);
        System.out.println("Премия: " + bonus);
        System.out.println("Штраф: " + burnedLavashPenalty);
        System.out.println("Итоговая зарплата: " + totalSalary);
        System.out.println("Шаур-выручка: " + shawarmaIncome);
        }
}
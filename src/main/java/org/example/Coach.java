package org.example;
import java.util.Random;

public class Coach {
    public static void main(String[] args) {
        Random random = new Random();

        //Подготовка данных посетителя
        double accountAmount = random.nextDouble(1000000.0);
        int visitorAge = random.nextInt(120);
        boolean visitorHasInvitation = false;

        //Начальные данные для проверки условий
        boolean isVisitorInBlackList = false;
        boolean isAgeValid = visitorAge >= 18;
        boolean isAccountAmountValid = accountAmount >= 50000.0;
        double mandatoryVoluntaryContribution = 0.075 * accountAmount;

        //Проверка возможности прохода
        boolean isAccessGranted = isAgeValid && (isAccountAmountValid || visitorHasInvitation) && !isVisitorInBlackList;

        //Вывод результатов проверок
        System.out.println("Возраст посетителя: " + visitorAge);
        System.out.println("Деньги на счете: " + accountAmount + " руб.");
        if (isAccessGranted) {
            System.out.println("Вход разрешен");
            System.out.println("Обязательный добровольный взнос: " + mandatoryVoluntaryContribution + " руб.");
        } else {
            System.out.println("Вход запрещен");
        }
    }
}
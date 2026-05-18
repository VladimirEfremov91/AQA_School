package org.lesson5;

import java.time.LocalDate;
import java.util.Random;

public class AlfaLab {
    public static void main(String[] args) {
        // Задаем необходимые переменные
        int minLength = 8; // минимальная длина
        int[] seed = {42, 77, 13};
        String prefix = "AGENT";

        // Выполняем задание
        String accessCode = generateAccessCode();
        System.out.println("Результат проверки: " + isValidCode(accessCode, minLength));
        logEvent("Server protection activated");
        logEvent("Intrusion attempt detected", true);
        for (int j : seed) {
            System.out.println(generateAgentId(prefix, j));
        }
    }

// Протокол ГЕНЕРАТОР
    public static String generateAccessCode() {
        String year = Integer.toString(LocalDate.now().getYear());
        int pow = (int)Math.pow(3,7);
        return year.concat("-").concat(Integer.toString(pow));
    }

// Протокол ВАЛИДАТОР
    private static boolean isValidCode(String code, int minLength) {
        return code != null && code.length() >= minLength && code.contains("-");
    }

// Протокол ЛОГГЕР
    public static void logEvent(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void logEvent(String message, boolean isCritical) {
        if (isCritical) {
            System.out.println("[CRITICAL] " + message);
        } else {
            logEvent(message);
        }
    }

// Протокол РАНДОМАЙЗЕР
    public static String generateAgentId(String prefix, int seed) {
        Random random = new Random(seed);
        int randomInt = Math.abs(random.nextInt(1000, 10000));
        return prefix.concat("-").concat(String.valueOf(randomInt));
    }
}

package org.lesson4;

public class QANightShift {
    public static void main(String[] args) {

        //Инициализируем необходимые переменные
        int autoTestQuantity = 100; // количество автотестов
        int finishedTestQuantity = 0; // счетчик пройденных тестов
        int passedTestQuantity = 0; // количество пройденных автотестов
        int flakyTestQuantity = 0; // количество нестабильных автотестов
        int bugQuantity = 0; // количество автотестов с реальным багом
        int criticalTestQuantity = 0; // количество автотестов с критическим багом и нестабильных
        int flakyTestCondition = 3; // условие нестабильного теста
        int bugCondition = 5; // условие теста с багом
        
        //Дополнительные переменные из бонус-задания
        boolean teamLeadWakingUp = true; // Будильник тим-лида
        int teamLeadWakingUpCondition = 3; // условие побудки тим-лида
        boolean showOnlyIssues = true; // Шумоподавление: Если включён — пропуск вывода тестов со статусом Pass

        //Выполняем автотест
        for (int i = 1; i <= autoTestQuantity; i++) {
            finishedTestQuantity++;
            if (i % flakyTestCondition == 0 && i % bugCondition == 0) {
                System.out.println("Тест #" + i + ": Critical!");
                criticalTestQuantity++;
                if (teamLeadWakingUp && criticalTestQuantity == teamLeadWakingUpCondition) {
                    System.out.println("\uD83D\uDEA8 Слишком много критических багов — будим тимлида!");
                    break;
                }
            } else if (i % bugCondition == 0) {
                System.out.println("Тест #" + i + ": Bug");
                bugQuantity++;
            } else if (i % flakyTestCondition == 0) {
                System.out.println("Тест #" + i + ": Flaky");
                flakyTestQuantity++;
            } else {
                if (!showOnlyIssues) {
                    System.out.println("Тест #" + i + ": Pass");
                }
                passedTestQuantity++;
            }
        }

        //Собираем сводку
        System.out.println("===== ИТОГИ НОЧНОЙ СМЕНЫ =====");
        System.out.println("Всего тестов: " + finishedTestQuantity);
        System.out.println("Pass: " + passedTestQuantity);
        System.out.println("Flaky: " + flakyTestQuantity);
        System.out.println("Bug: " + bugQuantity);
        System.out.println("Critical: " + criticalTestQuantity);
    }
}
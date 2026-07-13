package org.lesson13;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList <Alien> aliens = new ArrayList<>();
        aliens.addLast(new Alien("Дарт", "Татуин", 8));
        aliens.addLast(new Alien("Фредди", "Вязовая", 10));
        aliens.addLast(new Alien("Альф", "Альфа-Центавра", 1));
        aliens.addLast(new Alien("Глазастик", "Регулус", 3));
        aliens.addLast(new Alien("Глазастик", "Регулус", 6));

        //Часть 1: Ищем дубликаты пришельцев
        System.out.println("Часть 1: Ищем дубликаты пришельцев");
        boolean hasDuplicate = false;
        for (int i = 0; i < aliens.size(); i++) {
            for (int j = i + 1; j < aliens.size(); j++) {
                if (aliens.get(i).equals(aliens.get(j))) {
                    hasDuplicate = true;
                    break;
                }
            }
            if (hasDuplicate) {
                break;
            }
        }
        System.out.println("Список содержит дубликат: " + hasDuplicate);
        System.out.println("---------------------------------------------");

        // Часть 2: Формирование отрядов
        System.out.println("Часть 2: Формирование отрядов");
        SquadManager squadManager = new SquadManager();
        squadManager.demonstrateListCreations();
        System.out.println("---------------------------------------------");

        // Часть 3: Отсеивание трусов
        System.out.println("Часть 3: Отсеивание трусов");
        ArrayList <String> soldiers = new ArrayList<>();
        soldiers.addLast("Ваня");
        soldiers.addLast("Трус Петя");
        soldiers.addLast("Иван");
        soldiers.addLast("Трус Кузя");
        soldiers.addLast("Вано");
        squadManager.filterOutCowards(soldiers, "Трус");
        System.out.println("---------------------------------------------");

        // Часть 4: Очередь на вход
        System.out.println("Часть 4: Очередь на вход");
        AssaultQueue queue  = new AssaultQueue();
        String[] volunteers = {"Зефир", "Кефир", "Портной", "Кучерявый", "Степан"};
        for (String volunteer : volunteers) {
            queue.addRecruit(volunteer);
        }
        queue.printQueue();
        String coward1 = queue.retreatCoward();
        String coward2 = queue.retreatCoward();
        System.out.println(coward1 + " " + coward2);
        queue.addRecruit("Пегас");
        queue.addRecruit("Федя");
        queue.addRecruit("Аватар");
        queue.printQueue();
        System.out.println("---------------------------------------------");

        // Часть 5: Отчёт командованию
        System.out.println("Часть 5: Отчёт командованию");
        MissionReport missionReport = new MissionReport("Захват зоны 51", aliens , soldiers.size());
        System.out.println(missionReport);
        MissionReport missionReport1 = new MissionReport("Захват зоны 51", aliens , soldiers.size());
        System.out.println("Сравниваем отчеты через ==: " + (missionReport1 == missionReport));
        System.out.println("Сравниваем отчеты через equals: " + missionReport1.equals(missionReport));
        System.out.println("-------------------------------------------");
    }
}

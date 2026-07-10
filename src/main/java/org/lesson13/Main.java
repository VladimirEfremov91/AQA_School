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

        //Ищем дубликаты пришельцев
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
        SquadManager squadManager = new SquadManager();
        squadManager.demonstrateListCreations();
        System.out.println("---------------------------------------------");
    }
}

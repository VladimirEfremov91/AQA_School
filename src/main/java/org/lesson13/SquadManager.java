package org.lesson13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SquadManager {

    public void demonstrateListCreations() {

        // Основной отряд — обычный ArrayList
        ArrayList<String> mainSquad = new ArrayList<>();
        mainSquad.add("Чубакка");
        mainSquad.add("Лея");
        mainSquad.add("Хан");
        mainSquad.add("R2D2");

        try {
            mainSquad.add("Люк");
            System.out.println("Боец Люк успешно добавлен в Основной отряд");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }

        try {
            mainSquad.remove("Лея");
            System.out.println("Боец Лея успешно удален из Основного отряда");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }


        // Отряд поддержки — Arrays.asList()
        List<String> helpDeskSquad = Arrays.asList("Чип", "Дейл", "Рокфор");

        try {
            helpDeskSquad.add("Люк");
            System.out.println("Боец Люк успешно добавлен в Отряд поддержки");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }

        try {
            helpDeskSquad.remove("Чип");
            System.out.println("Боец Чип успешно удален из Отряда поддержки");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }

        // Элитный отряд — List.of()
        List<String> eliteSquad = List.of("Фред", "Джордж");

        try {
            eliteSquad.add("Люк");
            System.out.println("Боец Люк успешно добавлен в Элитный отряд");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }

        try {
            eliteSquad.remove("Фред");
            System.out.println("Боец Фред успешно удален из Элитного отряда");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }
    }

    public void filterOutCowards(List<String> squad, String keyWord) {
        System.out.println("Отряд до фильтрации: " + squad);
        Iterator<String> iterator = squad.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name.startsWith(keyWord)) {
                iterator.remove();
            }
        }
        System.out.println("Отряд после фильтрации по ключевому слову \"" + keyWord + "\": "  + squad);
    }
}
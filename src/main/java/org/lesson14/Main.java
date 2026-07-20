package org.lesson14;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        Задание 14.1 Кинцо
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Интерстеллар", 8.7));
        movies.add(new Movie("Шрек", 8.1));
        movies.add(new Movie("Начало", 8.8));
        movies.add(new Movie("Веном", 6.6));

        System.out.println("До сортировки:");
        System.out.println(movies);

        movies.sort(new MovieRatingComparator());

        System.out.println("После сортировки:");
        System.out.println(movies);
    }
}
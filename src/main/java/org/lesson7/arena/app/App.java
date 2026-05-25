package org.lesson7.arena.app;

import org.lesson7.arena.heroes.Archer;
import org.lesson7.arena.heroes.Hero;
import org.lesson7.arena.heroes.Knight;
import org.lesson7.arena.heroes.Mage;

public class App {
    public static void main(String[] args){
        Hero[] heroes = {
                new Knight("Арагорн", 4, 10, 10),
                new Archer("Леголас", 5, 20, 11),
                new Mage("Гендальф Белый", 100, 33, 31)
        };

        for (Hero hero : heroes) {
            hero.printInfo();
            hero.attack();
            System.out.println();
        }

//      Вишенка
        final Knight knight = new Knight("Фродо", 1, 33,12);
        System.out.println(knight);

//      Меняем значения полей и выводим на экран
        knight.takeDamage(12);
        System.out.println(knight);

        knight.levelUp();
        System.out.println(knight);

//      Количество созданных героев
        Hero.printHeroesCreated();
    }
}

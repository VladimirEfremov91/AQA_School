package org.lesson7.arena.heroes;

public class Hero {
    private String name;
    private int level;
    private int health;

    private static final int MAX_LEVEL = 100;
    private static int heroesCreated = 0;


    public Hero(String name, int level, int health) {
        this.name = name;
        this.level = level;
        this.health = health;
        heroesCreated++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public void printInfo() {
        System.out.println("Имя героя: " + name + ". Здоровье: " + health + ". Уровень: " + level + ".");
    }

    public void takeDamage(int damage) {
        if (damage > this.health) {
            this.health = 0;
        } else  {
            this.health -= damage;
        }
    }

    public void levelUp() {
        if (this.level < MAX_LEVEL) {
            this.level++;
        }
    }

    public void attack() {
        System.out.println("Герой наносит обычный удар.");
    }

    public void attack(String target) {
        System.out.println("Герой наносит обычный удар. Цель: " + target);
    }

    public void attack(String target, int times) {
        System.out.println("Герой атакует цель: " + target + " " +  times + " раза");
    }

    public static void printHeroesCreated() {
        System.out.println("Всего создано героев: " + heroesCreated);
    }

    public final void rest() {
        System.out.println("Герой отдыхает и восстанавливает силы");
    }

}

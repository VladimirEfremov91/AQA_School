package org.lesson15;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardGameTest {

    @Test
    void testCreateBoardGameNameValidation(String name) {
        String name = "";
        assertThrows(IllegalArgumentException.class,
                () -> new BoardGame(name, 20, 100.0),
                "Конструктор некорректно работает с исключениями при обработке неправильного имени");
    }

    @Test
    void testCreateBoardGameAgeValidation(String name) {
        int age = -1;
        assertThrows(IllegalArgumentException.class,
                () -> new BoardGame("Иван и 1001 ночь", age, 100.0),
                "Конструктор некорректно работает с исключениями при обработке неправильного возраста");
    }

    @Test
    void testCreateBoardGamePriceValidation(String name) {
        double price = -1.0;
        assertThrows(IllegalArgumentException.class,
                () -> new BoardGame("Иван и 1001 ночь", 20, price),
                "Конструктор некорректно работает с исключениями при обработке неправильной цены");
    }
}
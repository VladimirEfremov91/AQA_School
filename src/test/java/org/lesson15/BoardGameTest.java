package org.lesson15;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

public class BoardGameTest {

    @ParameterizedTest
    @CsvSource({
            "'Иван Васильевич меняет профессию', 18, 1.0",
            "'Для самых маленьких', 0, 250.0",
            "'Дурак', 3, 0"
    })
    void testCreateBoardGameHappyPath(String title, int minAge, double oneDayPrice) {
        BoardGame boardGame = new BoardGame(title, minAge, oneDayPrice);
        assertEquals(title, boardGame.getTitle(), "Конструктор некорректно обработал название игры");
        assertEquals(oneDayPrice, boardGame.getOneDayPrice(), "Конструктор некорректно обработал цену игры");
        assertEquals(minAge, boardGame.getMinAge(), "Конструктор некорректно обработал минимальный возраст игрока");
        assertFalse(boardGame.isRent(), "Конструктор некорректно обработал признак аренды");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testCreateBoardGameNameValidation(String title) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new BoardGame(title, 20, 100.0),
                "Конструктор должен выбрасывать IllegalArgumentException при некорректном названии игры: " + title
        );
    }

    @Test
    void testCreateBoardGameAgeValidation() {
        int age = -1;
        assertThrows(IllegalArgumentException.class,
                () -> new BoardGame("Иван и 1001 ночь", age, 100.0),
                "Конструктор должен выбрасывать IllegalArgumentException при обработке неправильного возраста");
    }

    @Test
    void testCreateBoardGamePriceValidation() {
        double price = -1.0;
        assertThrows(IllegalArgumentException.class,
                () -> new BoardGame("Иван и 1001 ночь", 20, price),
                "Конструктор должен выбрасывать IllegalArgumentException при обработке неправильной цены");
    }

    @ParameterizedTest
    @CsvSource({
            "10, false",
            "18, true",
            "23, true"
    })
    void testCanBeRentValidation(int age, boolean expectedResult) {
        BoardGame boardGame = new BoardGame("Иван Васильевич меняет профессию", 18, 2.2);
        assertEquals(expectedResult, boardGame.canBeRentedBy(age), "Результат проверки возраста клиента не совпал с ожидаемым"
        );
    }
}
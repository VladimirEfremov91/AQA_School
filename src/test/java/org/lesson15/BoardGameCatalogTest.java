package org.lesson15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardGameCatalogTest {

    private GameRental gameRental;

    @BeforeEach
    void setUp() {
        gameRental = new GameRental();
    }

    @Test
    void testAddGameHappyPath() {
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33);
        gameRental.addGame(boardGame);
        assertEquals(boardGame, gameRental.getBoardGameCatalog().getLast(),
                "Метод добавления игры в каталог работает некорректно");
    }

    @Test
    void testAddNullBoardGame() {
        assertThrows(IllegalArgumentException.class, () -> gameRental.addGame(null),
                "Метод должен выбрасывать IllegalArgumentException при добавлении null");
    }

    @Test
    void testAddSameGame() {
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33);
        gameRental.addGame(boardGame);

        assertThrows(
                IllegalArgumentException.class, () -> gameRental.addGame(boardGame),
                "Метод должен выбрасывать IllegalArgumentException при повторном добавлении игры");
    }

    @Test
    void testFindGameByTitleHappyPath() {
        BoardGame testGame = new BoardGame("testGame", 65, 3);
        gameRental.addGame(new BoardGame("Зомби 3", 18, 33));
        gameRental.addGame(new BoardGame("Холодное сердце", 3, 32));
        gameRental.addGame(testGame);
        BoardGame actualGame = gameRental.findBoardGameByTitle(testGame.getTitle());
        assertEquals(testGame, actualGame, "Метод должен находить игру по точному совпадению названия");
    }

    @Test
    void testFindGameByTitleNoGame() {
        String nonexistentTitle = "Проездной2";
        gameRental.addGame(new BoardGame("Зомби 3", 18, 33));
        gameRental.addGame(new BoardGame("Холодное сердце", 3, 32));
        BoardGame actualGame = gameRental.findBoardGameByTitle(nonexistentTitle);
        assertNull(actualGame, "Метод должен возвращать null, если игра с указанным названием не найдена");
    }

    @Test
    void testBoardGameResetHappyPath() {
        BoardGame rentedGame = new BoardGame("testGame", 65, 3);
        rentedGame.setRent(true);
        BoardGame availableGame = new BoardGame("Baba Yaga", 20, 33);
        gameRental.addGame(rentedGame);
        gameRental.addGame(availableGame);
        gameRental.reset();
        assertAll(
                "После reset() все игры должны иметь признак аренды false",
                () -> assertFalse(gameRental.findBoardGameByTitle("testGame").isRent(),
                        "Метод reset() не сбросил признак аренды у игры testGame"),
                () -> assertFalse(gameRental.findBoardGameByTitle("Baba Yaga").isRent(),
                        "Метод reset() некорректно обработал игру Baba Yaga")
        );
    }
}
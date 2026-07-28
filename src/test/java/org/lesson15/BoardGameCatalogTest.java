package org.lesson15;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardGameCatalogTest {
    @BeforeAll
    static void init() {
        GameRental gameRental = new GameRental();
    }

    @Test
    void testAddGameHappyPath() {
        GameRental gameRental = new GameRental();
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33);
        gameRental.addGame(boardGame);
        assertEquals(boardGame, gameRental.getBoardGameCatalog().getLast(),
        "Метод добавления игры в каталог работает некорректно");
    }

    @Test
    void testAddNullBoardGame() {
        GameRental gameRental = new GameRental();
        BoardGame boardGame = null;
        assertThrows(IllegalArgumentException.class, () -> gameRental.addGame(boardGame),
                "Конструктор должен выбрасывать IllegalArgumentException при добавлении null");
    }

    @Test
    void testAddSameGame() {
        GameRental gameRental = new GameRental();
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33);
        gameRental.addGame(boardGame);
        assertThrows(IllegalArgumentException.class, () -> gameRental.addGame(boardGame),
                "Конструктор должен выбрасывать IllegalArgumentException при повторном добавлении");
    }

    @Test
    void testFindGameByTittleHappyPath() {
        GameRental gameRental = new GameRental();
        BoardGame testGame = new BoardGame("testGame", 65, 3);
        gameRental.addGame(new BoardGame("Зомби 3", 18, 33));
        gameRental.addGame(new BoardGame("Холодное сердце", 3, 32));
        gameRental.addGame(testGame);
        assertEquals(testGame, gameRental.findBoardGameByTitle(testGame.getTitle()),
                "Метод должен находить игру по точному совпадению названия");
    }

    @Test
    void testFindGameByTittleNoGame() {
        GameRental gameRental = new GameRental();
        String testGame = "Проездной2";
        gameRental.addGame(new BoardGame("Зомби 3", 18, 33));
        gameRental.addGame(new BoardGame("Холодное сердце", 3, 32));
        assertNull(gameRental.findBoardGameByTitle(testGame),
                "Метод должен находить игру по точному совпадению названия");
    }

    @Test
    void testBoardGameResetHappyPath() {
        GameRental gameRental = new GameRental();
        BoardGame testGame = new BoardGame("testGame", 65, 3);
        testGame.setRent(true);
        gameRental.addGame(testGame);
        gameRental.addGame(new BoardGame("Baba Yaga", 20, 33));
        gameRental.reset();
        assertFalse(gameRental.findBoardGameByTitle("testGame").isRent());
        assertFalse(gameRental.findBoardGameByTitle("Baba Yaga").isRent());
    }
}

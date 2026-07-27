package org.lesson15;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardGameCatalogTest {
    @BeforeAll
    static void init() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
    }

    @Test
    void testAddGameHappyPath() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33.1);
        boardGameCatalog.addGame(boardGame);
        assertEquals(boardGame, boardGameCatalog.getBoardGameCatalog().getLast(),
        "Метод добавления игры в каталог работает некорректно");
    }

    @Test
    void testAddNullBoardGame() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        BoardGame boardGame = null;
        assertThrows(IllegalArgumentException.class, () -> boardGameCatalog.addGame(boardGame),
                "Конструктор должен выбрасывать IllegalArgumentException при добавлении null");
    }

    @Test
    void testAddSameGame() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33.1);
        boardGameCatalog.addGame(boardGame);
        assertThrows(IllegalArgumentException.class, () -> boardGameCatalog.addGame(boardGame),
                "Конструктор должен выбрасывать IllegalArgumentException при повторном добавлении");
    }

    @Test
    void testFindGameByTittleHappyPath() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        BoardGame testGame = new BoardGame("testGame", 65, 3.1);
        boardGameCatalog.addGame(new BoardGame("Зомби 3", 18, 33.1));
        boardGameCatalog.addGame(new BoardGame("Холодное сердце", 3, 32.1));
        boardGameCatalog.addGame(testGame);
        assertEquals(testGame, boardGameCatalog.findBoardGameByTitle(testGame.getTitle()),
                "Метод должен находить игру по точному совпадению названия");
    }

    @Test
    void testFindGameByTittleNoGame() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        String testGame = "Проездной2";
        boardGameCatalog.addGame(new BoardGame("Зомби 3", 18, 33.1));
        boardGameCatalog.addGame(new BoardGame("Холодное сердце", 3, 32.1));
        assertNull(boardGameCatalog.findBoardGameByTitle(testGame),
                "Метод должен находить игру по точному совпадению названия");
    }
}

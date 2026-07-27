package org.lesson15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardGameRentalTest {

    @Test
    void testRentBoardGameHappyPath() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        boardGameCatalog.addGame(new BoardGame("Зомби 3", 18, 33.1));
        assertTrue(boardGameCatalog.rentBoardGame("Зомби 3", 21),
                "Метод аренды вернул неожиданный ответ");
        assertTrue(boardGameCatalog.findBoardGameByTitle("Зомби 3").isRent(),
                "Метод аренды не проставил признак того, что игра в аренде");
    }

    @Test
    void testRentBoardGameNotFound() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        boardGameCatalog.addGame(new BoardGame("Зомби 3", 18, 33.1));
        assertThrows(IllegalArgumentException.class,
                () -> boardGameCatalog.rentBoardGame("Зомби 4", 21),
                "Метод IllegalArgumentException при несуществующем названии");
    }

    @Test
    void testRentBoardGameInvalidClientAge() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        boardGameCatalog.addGame(new BoardGame("Зомби 3", 18, 33.1));
        assertFalse(boardGameCatalog.rentBoardGame("Зомби 3", 3),
                "Метод аренды вернул неожиданный ответ");
    }

    void testRentBoardGameAlreadyRented() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33.1);
        boardGame.setRent(true);
        assertFalse(boardGameCatalog.rentBoardGame("Зомби 3", 21),
                "Метод аренды вернул неожиданный ответ");
    }


}

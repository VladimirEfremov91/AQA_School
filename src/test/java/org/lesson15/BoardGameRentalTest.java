package org.lesson15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardGameRentalTest {

    private final BoardGame TEST_GAME = new BoardGame("Зомби 3", 18, 33.1);

    @Test
    void testRentBoardGameHappyPath() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        boardGameCatalog.addGame(TEST_GAME);
        assertTrue(boardGameCatalog.rentGame("Зомби 3", 21),
                "Метод аренды вернул неожиданный ответ");
        assertTrue(boardGameCatalog.findBoardGameByTitle("Зомби 3").isRent(),
                "Метод аренды не проставил признак того, что игра в аренде");
    }

    @Test
    void testRentBoardGameNotFound() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        boardGameCatalog.addGame(TEST_GAME);
        assertThrows(IllegalArgumentException.class,
                () -> boardGameCatalog.rentGame("Зомби 4", 21),
                "Метод IllegalArgumentException при несуществующем названии");
    }

    @Test
    void testRentBoardGameInvalidClientAge() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        boardGameCatalog.addGame(TEST_GAME);
        assertFalse(boardGameCatalog.rentGame("Зомби 3", 3),
                "Метод аренды вернул неожиданный ответ");
    }

    @Test
    void testRentBoardGameAlreadyRented() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33.1);
        boardGame.setRent(true);
        boardGameCatalog.addGame(boardGame);
        assertFalse(boardGameCatalog.rentGame("Зомби 3", 21),
                "Метод аренды вернул неожиданный ответ");
    }

    @Test
    void testReturnBoardGameHappyPath() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33.1);
        boardGame.setRent(true);
        boardGameCatalog.addGame(boardGame);
        assertTrue(boardGameCatalog.returnGame("Зомби 3"),
                "Метод returnBoardGame должен вернуть true при успешном возврате арендованной игры");
        assertFalse(boardGame.isRent(),
                "После успешного возврата игра не должна иметь признак аренды"
        );
    }

    @Test
    void testReturnBoardGameNotFound() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        boardGameCatalog.addGame(new BoardGame("Зомби 3", 18, 33.1));
        assertFalse(boardGameCatalog.returnGame("Зомби 4"),
                "Метод returnBoardGame должен вернуть false при возврате несуществующей игры");
    }

    @Test
    void testReturnBoardGameNotRented() {
        BoardGameCatalog boardGameCatalog = new BoardGameCatalog();
        boardGameCatalog.addGame(new BoardGame("Зомби 3", 18, 33.1));
        assertFalse(boardGameCatalog.returnGame("Зомби 3"),
                "Метод returnBoardGame должен вернуть false при возврате неарендованной игры");
    }


}

package org.lesson15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardGameRentalTest {

    private final BoardGame TEST_GAME = new BoardGame("Зомби 3", 18, 33);

    @Test
    void testRentBoardGameHappyPath() {
        GameRental gameRental = new GameRental();
        gameRental.addGame(TEST_GAME);
        assertTrue(gameRental.rentGame("Зомби 3", 21),
                "Метод аренды вернул неожиданный ответ");
        assertTrue(gameRental.findBoardGameByTitle("Зомби 3").isRent(),
                "Метод аренды не проставил признак того, что игра в аренде");
    }

    @Test
    void testRentBoardGameNotFound() {
        GameRental gameRental = new GameRental();
        gameRental.addGame(TEST_GAME);
        assertThrows(IllegalArgumentException.class,
                () -> gameRental.rentGame("Зомби 4", 21),
                "Метод IllegalArgumentException при несуществующем названии");
    }

    @Test
    void testRentBoardGameInvalidClientAge() {
        GameRental gameRental = new GameRental();
        gameRental.addGame(TEST_GAME);
        assertFalse(gameRental.rentGame("Зомби 3", 3),
                "Метод аренды вернул неожиданный ответ");
    }

    @Test
    void testRentBoardGameAlreadyRented() {
        GameRental gameRental = new GameRental();
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33);
        boardGame.setRent(true);
        gameRental.addGame(boardGame);
        assertFalse(gameRental.rentGame("Зомби 3", 21),
                "Метод аренды вернул неожиданный ответ");
    }

    @Test
    void testReturnBoardGameHappyPath() {
        GameRental gameRental = new GameRental();
        BoardGame boardGame = new BoardGame("Зомби 3", 18, 33);
        boardGame.setRent(true);
        gameRental.addGame(boardGame);
        assertTrue(gameRental.returnGame("Зомби 3"),
                "Метод returnBoardGame должен вернуть true при успешном возврате арендованной игры");
        assertFalse(boardGame.isRent(),
                "После успешного возврата игра не должна иметь признак аренды"
        );
    }

    @Test
    void testReturnBoardGameNotFound() {
        GameRental gameRental = new GameRental();
        gameRental.addGame(new BoardGame("Зомби 3", 18, 33));
        assertFalse(gameRental.returnGame("Зомби 4"),
                "Метод returnBoardGame должен вернуть false при возврате несуществующей игры");
    }

    @Test
    void testReturnBoardGameNotRented() {
        GameRental gameRental = new GameRental();
        gameRental.addGame(new BoardGame("Зомби 3", 18, 33));
        assertFalse(gameRental.returnGame("Зомби 3"),
                "Метод returnBoardGame должен вернуть false при возврате неарендованной игры");
    }
}

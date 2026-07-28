package org.lesson15;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RentalCostTest {

    @Test
    public void testCalculateCostHappyPath() {
        GameRental gameRental = new GameRental();
        gameRental.addGame(new BoardGame("Tom & Jerry", 3, 10));
        int days = 10;
        double testCost = gameRental.findBoardGameByTitle("Tom & Jerry").getOneDayPrice() * days;
        assertEquals(testCost, gameRental.calculateCost("Tom & Jerry", days),
                "Метод calculateCost должен вернуть произведение цены на количество дней");
    }

    @ParameterizedTest(name = "{index}: название={0}, дни={1}")
    @MethodSource("invalidCalculateCostArguments")
    void testCalculateCostInvalidArguments(String title, int days, boolean addGame, String assertionMessage) {
        GameRental boardGameCatalog = new GameRental();
        if (addGame) {
            boardGameCatalog.addGame(new BoardGame("Tom & Jerry", 3, 10));
        }
        assertThrows(IllegalArgumentException.class, () -> boardGameCatalog.calculateCost(title, days),
                assertionMessage);
    }
    static Stream<Arguments> invalidCalculateCostArguments() {
        return Stream.of(
                Arguments.of("Несуществующая игра", 10, false,
                        "Метод должен выбросить IllegalArgumentException, если игра отсутствует в каталоге"
                ),
                Arguments.of("Tom & Jerry", 0, true,
                        "Метод должен выбросить IllegalArgumentException, если количество дней равно нулю"
                ),
                Arguments.of("Tom & Jerry", -1, true,
                        "Метод должен выбросить IllegalArgumentException, если количество дней меньше нуля"
                )
        );
    }


}

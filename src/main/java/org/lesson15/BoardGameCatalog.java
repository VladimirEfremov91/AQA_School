package org.lesson15;

import java.util.ArrayList;
import java.util.List;

public class BoardGameCatalog {

    private final List<BoardGame> boardGameCatalog = new ArrayList<>();

    public void addGame(BoardGame game) {
        if (game == null) {
            throw new IllegalArgumentException("Нельзя добавить null вместо настольной игры");
        }
        for (BoardGame boardGame : boardGameCatalog) {
            if (boardGame.getTitle().equals(game.getTitle())) {
                throw new IllegalArgumentException("Игра с названием \"" + game.getTitle() + "\" уже есть в каталоге");
            }
        }
        boardGameCatalog.add(game);
    }

    public List<BoardGame> getBoardGameCatalog() {
        return new ArrayList<>(boardGameCatalog);
    }

    public BoardGame findBoardGameByTitle(String title) {
        if (title == null) {throw new IllegalArgumentException("Название игры не может быть null");}
        for (BoardGame game : boardGameCatalog) {
            if (title.equals(game.getTitle())) {
                return game;
            }
        }
        return null;
    }

    public boolean rentGame(String title, int customerAge) {
        BoardGame boardGame = findBoardGameByTitle(title);
        if (boardGame == null) {throw new IllegalArgumentException("Игра отсутствует в каталоге");}
        if (!boardGame.canBeRentedBy(customerAge)) {
            return false;
        }
        if (boardGame.isRent()) {
            return false;
        }
        boardGame.setRent(true);
        return true;
    }

    public boolean returnGame(String title) {
        BoardGame boardGame = findBoardGameByTitle(title);
        if (boardGame == null) {
            return false;
        }
        if (!boardGame.isRent()) {
            return false;
        }
        boardGame.setRent(false);
        return true;
    }

}
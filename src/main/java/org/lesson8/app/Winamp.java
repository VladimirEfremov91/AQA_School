package org.lesson8.app;

import org.lesson8.model.Playlist;

import java.util.ArrayList;

public class Winamp {
    public static void main(String[] args) {
        Playlist playlist = new Playlist("Кринж на проде", new ArrayList<>());

        playlist.addSong("Шаман - Я русский");
        playlist.addSong("Алла Пугачева - Примадонна");
        playlist.addSong("Тату - Нас не догонят");
        playlist.addSong("Филипп Киркоров - Не та дверь");
        playlist.getSongName(2);
        playlist.removeSong(1);
        playlist.updateSong(0, "Аркадий Укупник - Зараза");

        System.out.println(playlist);
    }
}

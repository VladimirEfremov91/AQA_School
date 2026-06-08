package org.lesson8.model;

import java.util.ArrayList;

public class Playlist {
    private String playlistName;
    private ArrayList<String> songsList;

    public Playlist(String playlistName, ArrayList<String> songsList) {
        this.playlistName = playlistName;
        this.songsList = songsList;
    }

    public void addSong(String songName) {
        songsList.add(songName);
        System.out.println("В плейлист добавлена песня: '" + songName + "'");
    }

    public void removeSong(int index) {
        String songForDeletion = songsList.remove(index);
        System.out.println("Из плейлиста удалена песня: '" + songForDeletion + "'");
    }

    public void updateSong(int index, String songName) {
        String oldSong = songsList.set(index, songName);
        System.out.println("Плейлист обновлен: песня '" + oldSong + "' заменена на '" + songName + "'");
    }

    public String getSongName(int index) {
        String song = songsList.get(index);
        System.out.println("Из плейлиста получена песня: '" + song + "'");
        return song;
    }

    @Override
    public String toString() {
        return "Playlist {" +
                "Название плейлиста = '" + playlistName + '\'' +
                ", Список песен = " + songsList +
                '}';
    }
}
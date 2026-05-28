package org.lesson8.model;

import java.util.ArrayList;

public class Playlist {
    private String playlistName;
    private ArrayList<String> songsList;

    public Playlist(String playlistName, ArrayList<String> songsList) {
        this.playlistName = playlistName;
        this.songsList = songsList;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public ArrayList<String> getSongsList() {
        return songsList;
    }

    public void setSongsList(ArrayList<String> songsList) {
        this.songsList = songsList;
    }

//    public Playlist() {}

    //  Добавление песни в плейлист
    public void addSong (String songName) {
        songsList.add(songName);
        System.out.println("В плейлист добавлена песня: '" + songName + "'");
    }

    //  Удаление песни из плейлиста
    public void removeSong (int index) {
        String songForDeletion = songsList.get(index);
        songsList.remove(index);
        System.out.println("Из плейлиста удалена песня: '" + songForDeletion + "'");
    }

    //  Обновление песни в плейлисте по индексу
    public void updateSong (int index, String songName) {
        String oldSong = songsList.get(index);
        songsList.remove(index);
        songsList.add(index, songName);
        System.out.println("Плейлист обновлен: песня '" + oldSong + "' заменена на '" + songName + "'");
    }

//    Получение песни по индексу
    public String getSongName(int index) {
        System.out.println("Из плейлиста получена песня: '" + songsList.get(index) + "'");
        return songsList.get(index);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistName='" + playlistName + '\'' +
                ", songsList=" + songsList +
                '}';
    }
}

package org.lesson13;

import java.util.List;

public class MissionReport {
    private String missionName;
    private List capturedAliens;
    private int squadSize;

    public MissionReport(String missionName, List capturedAliens, int squadSize) {
        this.missionName = missionName;
        this.capturedAliens = capturedAliens;
        this.squadSize = squadSize;
    }

    @Override
    public String toString() {
        return "Отчет: \n" +
                "  Название задания: " + missionName +
                ",\n  Поймано пришельцев: " + capturedAliens.size() +
                ",\n  Размер отряда: " + squadSize;
    }
}
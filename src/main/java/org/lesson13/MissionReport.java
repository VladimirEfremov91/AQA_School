package org.lesson13;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MissionReport that = (MissionReport) o;
        return squadSize == that.squadSize && Objects.equals(missionName, that.missionName) && Objects.equals(capturedAliens, that.capturedAliens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionName, capturedAliens, squadSize);
    }
}
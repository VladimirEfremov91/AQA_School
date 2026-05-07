package org.lesson5;

import java.time.LocalDate;

public class AlfaLab {
    public static void main(String[] args) {}

    public String generateAccesToken() {
        String accessToken;
        accessToken = toString(LocalDate.now().getYear());
        return accessToken;
    }

    private String toString(int year) {
    }
}

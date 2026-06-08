package org.lesson9;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        Generator generator = new Generator();

//        Оставил тут всего два метода, всё остальное спрятано в генераторе
        generator.printLogo();
        generator.printLegalInfo();
    }
}

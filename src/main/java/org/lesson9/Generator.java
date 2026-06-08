package org.lesson9;

import com.github.lalyos.jfiglet.FigletFont;
import net.datafaker.Faker;
import java.io.IOException;
import java.util.Random;

public class Generator {

    private final Faker faker = new Faker();
    private final Random random = new Random();

    private final String[] lastNames = {
            "Smith",
            "Borisoff",
            "Nigger",
            "Goldfinger"
    };

//    Генерим фамилию факером
    private String generateLastNameFromFaker() {
        return faker.name().lastName();
    }

//    Генерим фамилию рандомом из массива
    private String generateLastNameFromArray() {
        int index = random.nextInt(lastNames.length);
        return lastNames[index];
    }

//    Генерим юридическую информацию
    private String generateLegalData() {
        String ceo = faker.name().fullName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String address = faker.address().fullAddress();
        return String.join(System.lineSeparator(), address, phoneNumber, ceo);
    }

//    Выводим лого
    public void printLogo() throws IOException {
        String firstLine = generateLastNameFromArray();
        String ampersand = "&";
        String lastLine = generateLastNameFromFaker();
        System.out.println(FigletFont.convertOneLine(firstLine));
        System.out.println(FigletFont.convertOneLine(ampersand));
        System.out.println(FigletFont.convertOneLine(lastLine));
    }

//    Выводим юридическую информацию
    public void printLegalInfo() {
        System.out.println(generateLegalData());
    }
}
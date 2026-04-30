package org.lesson4;

import java.util.Scanner;

public class SecretMessageDecoder {
    public static void main(String[] args) {

        //Инициализируем необходимые переменные
        Scanner scanner = new Scanner(System.in);
        int messagePartQuantity = 5; // количество частей сообщения
        String reservePart = "XX"; // резервный фрагмент на случай ошибки
        String[] secretMessage = new String[messagePartQuantity]; // массив фрагментов сообщения
        String messagePart; // часть сообщения
        String secretMessageString = new String(); // единое сообщение в одном месте

        //Запрашиваем у пользователя части сообщения и записываем в массив
        for (int i = 0; i < secretMessage.length; i++) {
            System.out.println("Введите " + (i + 1) + " часть сообщения:");
            messagePart = scanner.nextLine();
            if (messagePart.equalsIgnoreCase("NULL")) {
                System.out.println("Часть сообщения повреждена! Используем резервный фрагмент: " + reservePart);
                secretMessage[i] = reservePart;
            } else {
            secretMessage[i] = messagePart;
            }
        }

        //Собираем сообщение
        for (int i = 0; i < secretMessage.length; i++) {
            secretMessageString = secretMessageString.concat((secretMessage[i] + "#"));
        }
        secretMessageString = secretMessageString.substring(0, secretMessageString.length() - 1);
        System.out.println(" Расшифрованное послание: " + secretMessageString);
    }
}
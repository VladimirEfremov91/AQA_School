package org.lesson3;

import java.util.Arrays;

public class Validator {
    public static void main(String[] args) {
        String[] passwords = {"курицаsss", "1qwertywwwqZ", "2qwewtywwwqz"};

        String password;
        for (int i = 0; i < passwords.length; i++) {
            password = passwords[i];
            boolean result = password.length() > 8 &&
                    !password.startsWith("1") &&
                    !password.endsWith("z") &&
                    !password.contains("1234") &&
                    !password.contains("qwerty");
            System.out.println("Пароль " + password + " прошел проверку: " + result);
        }
    }
}

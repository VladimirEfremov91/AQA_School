package org.lesson19;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HerokuappSelenideTest {

    @Test
    public void userCanLoginWithValidCredentialsAndLogout() {
//      1. Открыть страницу https://the-internet.herokuapp.com/
        open("https://the-internet.herokuapp.com/");

//      2. Кликнуть по ссылке с текстом Form Authentication
        $x("//a[normalize-space()='Form Authentication']").click();

//      3. Проверить что заголовок страницы содержит текст Login Page
        $x("//h2[normalize-space()='Login Page']").shouldHave(text("Login Page"));

//      4. Установить в Username значение tomsmith
        $x("//input[@name = 'username']").setValue("tomsmith");

//      5. Установить в поле Password значение SuperSecretPassword!
        $x("//input[@name = 'password']").setValue("SuperSecretPassword!");

//      6. Нажать кнопку Login
        $x("//i[normalize-space()='Login']").click();

//      7. Найти сообщение результата логина и проверить, что оно содержит текст You logged into a secure area!
        $x("//div[@class = 'flash success']").shouldHave(text("You logged into a secure area!"));

//      8. Проверить что на экране присутствует кнопка Logout
        $x("//i[normalize-space()='Logout']").shouldBe(visible);

//      9. Нажать на кнопку Logout
        $x("//i[normalize-space()='Logout']").click();

//      10. Проверить что страница на которую произошел переход - имеет заголовок с текстом Login Page
        $x("//h2[normalize-space()='Login Page']").shouldHave(text("Login Page"));
    }

    @Test
    public void userCannotLoginWithInvalidCredentials() {
//      1. Открыть страницу https://the-internet.herokuapp.com/
        open("https://the-internet.herokuapp.com/");

//      2. Кликнуть по ссылке с текстом Form Authentication
        $x("//a[normalize-space()='Form Authentication']").click();

//      3. Проверить что внизу страницы есть ссылка с текстом Elemental Selenium
        $x("//a[@target='_blank']").shouldHave(text("Elemental Selenium"));

//      4. Установить в Username значение admin
        $x("//input[@name = 'username']").setValue("admin");

//      5. Установить в поле Password значение 1234
        $x("//input[@name = 'password']").setValue("1234");

//      6. Нажать кнопку Login
        $x("//i[normalize-space()='Login']").click();

//      7. Найти сообщение результата логина и проверить, что оно содержит текст Your username is invalid!
        $x("//div[@class = 'flash error']").shouldHave(text("Your username is invalid!"));
    }

}

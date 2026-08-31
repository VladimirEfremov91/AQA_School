package org.lesson19.steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.lesson19.pages.LoginPage;
import org.lesson19.pages.MainPage;
import org.lesson19.pages.SecurePage;

public class Steps {
    private final MainPage mainPage = new MainPage();
    private final LoginPage loginPage = new LoginPage();
    private final SecurePage securePage = new SecurePage();

    @Дано("установлен корневой URL сайта")
    public static void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
    }

    @Дано("открыта главная страница сайта")
    public void openMainPage() {
        mainPage.openMainPage();
    }

    @Когда("пользователь переходит в Form Authentication")
    public void openFormAuthentication() {
        mainPage.clickAuthenticationLink();
    }

    @Тогда("заголовок страницы авторизации содержит текст {string}")
    public void checkLoginHeader(String expectedText) {
        loginPage.checkLoginHeaderText(expectedText);
    }

    @Тогда("внизу страницы есть ссылка с текстом {string}")
    public void checkElementalLink(String expectedText) {
        loginPage.checkElementalLinkText(expectedText);
    }

    @Когда("пользователь вводит логин {string} и пароль {string}")
    public void setCredentials(String username, String password) {
        loginPage.setUserCredentials(username, password);
    }

    @И("пользователь нажимает кнопку Login")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Тогда("отображается сообщение успешной авторизации {string}")
    public void checkSuccessfulLogin(String expectedText) {
        securePage.checkLoginSuccessAlertText(expectedText);
    }

    @И("отображается кнопка Logout")
    public void checkLogoutButton() {
        securePage.checkLogoutButtonVisibility();
    }

    @Когда("пользователь нажимает кнопку Logout")
    public void clickLogoutButton() {
        securePage.logoutButtonClick();
    }

    @Тогда("отображается сообщение ошибки авторизации {string}")
    public void checkLoginError(String expectedText) {
        loginPage.checkErrorLoginAlertText(expectedText);
    }
}
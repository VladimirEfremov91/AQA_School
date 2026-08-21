package org.lesson19;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.lesson19.pages.LoginPage;
import org.lesson19.pages.MainPage;
import org.lesson19.pages.SecurePage;

public class HerokuappPOMTest {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    SecurePage securePage = new SecurePage();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
    }

    @Test
    public void userCanLoginWithValidCredentialsAndLogout() {
        mainPage.openMainPage();
        mainPage.clickAuthenticationLink();
        loginPage.checkLoginHeaderText("Login Page");
        loginPage.setUserCredentials("tomsmith", "SuperSecretPassword!");
        loginPage.clickLoginButton();
        securePage.checkLoginSuccessAlertText("You logged into a secure area!");
        securePage.checkLogoutButtonVisibility();
        securePage.logoutButtonClick();
        loginPage.checkLoginHeaderText("Login Page");
    }

    @Test
    public void userCannotLoginWithInvalidCredentials() {
        mainPage.openMainPage();
        mainPage.clickAuthenticationLink();
        loginPage.checkElementalLinkText("Elemental Selenium");
        loginPage.setUserCredentials("admin", "1234");
        loginPage.clickLoginButton();
        loginPage.checkErrorLoginAlertText("Your username is invalid!");
    }
}

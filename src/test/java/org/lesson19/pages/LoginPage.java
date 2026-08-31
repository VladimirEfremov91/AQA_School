package org.lesson19.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private static final String PAGE_URL = "/login";

    private SelenideElement usernameInput = $x("//input[@name = 'username']");
    private SelenideElement passwordInput = $x("//input[@name = 'password']");
    private SelenideElement loginButton = $x("//i[normalize-space()='Login']");
    private SelenideElement loginHeader = $x("//h2[normalize-space()='Login Page']");
    private SelenideElement elementalLink = $x("//a[@target='_blank']");
    private SelenideElement errorLoginAlert =  $x("//div[@class = 'flash error']");

    public void setUserCredentials(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void checkLoginHeaderText(String headerText) {
        loginHeader.shouldHave(text(headerText));
    }

    public void checkErrorLoginAlertText(String errorLoginAlertText) {
        errorLoginAlert.shouldHave(text(errorLoginAlertText));
    }

    public void checkElementalLinkText(String elementalLinkText) {
        elementalLink.shouldHave(text(elementalLinkText));
    }
}
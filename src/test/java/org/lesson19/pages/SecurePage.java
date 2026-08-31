package org.lesson19.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class SecurePage {
    private static final String PAGE_URL = "/secure";

    private SelenideElement loginSuccessAlert = $x("//div[@class = 'flash success']");
    private SelenideElement logoutButton = $x("//i[normalize-space()='Logout']");

    public void logoutButtonClick() {
        logoutButton.click();
    }

    public void checkLoginSuccessAlertText(String loginSuccessAlertText) {
        loginSuccessAlert.shouldHave(text(loginSuccessAlertText));
    }

    public void checkLogoutButtonVisibility() {
        logoutButton.shouldBe(visible);
    }
}
package org.lesson19.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private static final String PAGE_URL = "";

    private SelenideElement AuthenticationLink = $x("//a[normalize-space()='Form Authentication']");

    public void openMainPage() {
        open(PAGE_URL);
    }

    public void clickAuthenticationLink() {
        AuthenticationLink.click();
    }
}

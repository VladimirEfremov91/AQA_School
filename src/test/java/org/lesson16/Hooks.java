package org.lesson16;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void printScenarioName(Scenario scenario) {
        System.out.println("Начинается сценарий: " + scenario.getName());
    }

    @After
    public void printScenarioResult(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Сценарий упал: " + scenario.getName());
        } else {
            System.out.println("Сценарий прошёл успешно: " + scenario.getName() + "\n");
        }
    }
}
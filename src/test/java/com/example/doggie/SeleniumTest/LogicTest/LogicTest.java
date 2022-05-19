package com.example.doggie.SeleniumTest.LogicTest;

import com.example.doggie.SeleniumTest.Settings;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LogicTest extends Settings {

    @Test
    public void MealGenerate() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LogicPage logicPage = PageFactory.initElements(driver, LogicPage.class);

        logicPage.open();
        logicPage.goToLogin();
        logicPage.fillLogInData();
        logicPage.goToMeal();
        logicPage.generateMeal();

    }

    @Test
    public void LogIn() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        LogicPage logicPage = PageFactory.initElements(driver, LogicPage.class);

        logicPage.open();
        logicPage.goToLogin();
        logicPage.fillLogInData();

    }

}

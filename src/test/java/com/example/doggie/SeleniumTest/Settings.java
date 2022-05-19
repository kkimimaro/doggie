package com.example.doggie.SeleniumTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Settings {
    public WebDriver driver;

    @BeforeEach
    public void BeforeTest() {
        System.setProperty("webdriver.chrome.driver", "/chromedrivernew/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}

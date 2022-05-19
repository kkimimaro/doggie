package com.example.doggie.SeleniumTest.LogicTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LogicPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "[id=\"loginBtn\"]")
    public WebElement loginButton;

    @FindBy(css = "[name=\"login\"]")
    public WebElement goLoginButton;

    @FindBy(css = "[name=\"username\"]")
    public WebElement loginInput;

    @FindBy(css = "[name=\"password\"]")
    public WebElement passwordInput;

    @FindBy(css = "[id=\"mealGoBtn\"]")
    public WebElement mealButton;

    @FindBy(css = "[id=\"textField\"]")
    public WebElement mealGenerateField;

    @FindBy(css = "[id=\"mealBtn\"]")
    public WebElement mealGenerateButton;

    @FindBy(css = "[id=\"mealDownloadBtn\"]")
    public WebElement mealDownloadButton;

    public LogicPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void open() {
        driver.get("http://localhost:8080/");
    }

    public void goToLogin() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("login"));
    }

    public void fillLogInData() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(loginInput));
        loginInput.sendKeys("naruto");
        passwordInput.sendKeys("naruto");
        goLoginButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mealButton));
    }

    public void goToMeal() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mealButton.click();
        wait.until(ExpectedConditions.urlContains("meal"));
    }

    public void generateMeal() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mealGenerateField.sendKeys("План питания");
        mealGenerateButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mealDownloadButton));
    }
}

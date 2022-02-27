package com.epam.tc.hw3.page.objects.voids;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriverWait wait;
    private final String title;

    @FindBy(id = "user-icon")
    private WebElement loginForm;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "user-name")
    private WebElement userNameInfo;



    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        title = driver.getTitle();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLoginForm() {
        loginForm.click();
    }

    public void sendKeysName(final String usernameText) {
        name.sendKeys(usernameText);
    }

    public void sendKeysPassword(final String passwordText) {
        password.sendKeys(passwordText);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getTitle() {
        return title;
    }

    public String getUserNameInfo() {
        // wait until the username is no more hidden
        return wait.until(ExpectedConditions.visibilityOf(userNameInfo)).getText();
    }
}

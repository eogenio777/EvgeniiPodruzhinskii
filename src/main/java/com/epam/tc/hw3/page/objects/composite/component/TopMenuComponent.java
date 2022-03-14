package com.epam.tc.hw3.page.objects.composite.component;

import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TopMenuComponent extends AbstractJdiBaseComponent {

    @FindBy(xpath = "//nav/ul/li/a[not(@href='#')]")
    private List<WebElement> navMenu;

    @FindBy(css = "li.dropdown > a.dropdown-toggle")
    private WebElement serviceDropdown;

    @FindBy(css = "ul.dropdown-menu a[href=\"user-table.html\"]")
    private WebElement userTableButton;

    @FindBy(css = "ul.dropdown-menu a[href=\"different-elements.html\"]")
    private WebElement differentElementsButton;

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

    public TopMenuComponent(WebDriver driver) {
        super(driver);
    }

    public long getNavMenuSize() {
        return navMenu.size();
    }

    public List<String> getNavMenuTitles() {
        return navMenu
            .stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
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

    public String getUserNameInfo() {
        // wait until the username is no more hidden
        return wait.until(ExpectedConditions.visibilityOf(userNameInfo)).getText();
    }

    public void clickServiceDropdown() {
        serviceDropdown.click();
    }

    public void clickDifferentElementsButton() {
        wait.until(ExpectedConditions.visibilityOf(differentElementsButton));
        differentElementsButton.click();
    }

    public void clickUserTableButton() {
        wait.until(ExpectedConditions.visibilityOf(userTableButton));
        userTableButton.click();
    }
}

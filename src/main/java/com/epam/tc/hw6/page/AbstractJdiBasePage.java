package com.epam.tc.hw6.page;

import com.epam.tc.hw6.page.component.LeftMenuComponent;
import com.epam.tc.hw6.page.component.TopMenuComponent;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractJdiBasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected TopMenuComponent topMenu;
    protected LeftMenuComponent leftMenu;
    private static final String BASE_URL = "https://jdi-testing.github.io/jdi-light/";

    protected AbstractJdiBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        topMenu = new TopMenuComponent(driver);
        leftMenu = new LeftMenuComponent(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public TopMenuComponent topMenu() {
        return this.topMenu;
    }

    public LeftMenuComponent leftMenu() {
        return this.leftMenu;
    }

    public void open(String url) {
        driver.get(BASE_URL + url);
    }
}

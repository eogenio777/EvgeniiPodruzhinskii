package com.epam.tc.hw3.page.objects.composite;

import com.epam.tc.hw3.page.objects.composite.component.LeftMenuComponent;
import com.epam.tc.hw3.page.objects.composite.component.TopMenuComponent;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractJdiBasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected TopMenuComponent topMenu;
    protected LeftMenuComponent leftMenu;

    protected AbstractJdiBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, this);
        topMenu = new TopMenuComponent(driver);
        leftMenu = new LeftMenuComponent(driver);
    }

    public TopMenuComponent topMenu() {
        return this.topMenu;
    }

    public LeftMenuComponent leftMenu() {
        return this.leftMenu;
    }
}

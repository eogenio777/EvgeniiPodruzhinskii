package com.epam.tc.hw3.page.objects.composite.component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FrameComponent extends AbstractJdiBaseComponent {

    @FindBy(xpath = "/html/body/span/div/div/input")
    private WebElement frameButton;

    public FrameComponent(WebDriver driver) {
        super(driver);
    }

    public boolean isFrameButtonDisplayed() {
        return frameButton.isDisplayed();
    }
}

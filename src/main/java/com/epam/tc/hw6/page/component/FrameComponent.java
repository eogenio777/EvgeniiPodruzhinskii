package com.epam.tc.hw6.page.component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FrameComponent extends AbstractJdiBaseComponent {

    @FindBy(id = "frame-button")
    private WebElement frameButton;

    public FrameComponent(WebDriver driver) {
        super(driver);
    }

    public boolean isFrameButtonDisplayed() {
        return frameButton.isDisplayed();
    }
}

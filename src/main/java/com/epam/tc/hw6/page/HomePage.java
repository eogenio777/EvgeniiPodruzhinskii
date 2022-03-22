package com.epam.tc.hw6.page;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractJdiBasePage {

    @FindBy(className = "icons-benefit")
    private List<WebElement> icons;

    @FindBy(className = "benefit-txt")
    private List<WebElement> iconTexts;

    @FindBy(id = "frame")
    private WebElement frame;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public long getIconsCount() {
        return icons.size();
    }

    public boolean areIconsDisplayed() {
        for (WebElement icon : icons) {
            if (!icon.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public List<String> getIconsTexts() {
        return iconTexts
            .stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    }

    public long getIconsTextsCount() {
        return iconTexts.size();
    }

    public boolean isFrameDisplayed() {
        return frame.isDisplayed();
    }
}

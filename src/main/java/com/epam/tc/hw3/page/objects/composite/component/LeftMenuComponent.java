package com.epam.tc.hw3.page.objects.composite.component;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeftMenuComponent extends AbstractJdiBaseComponent {
    @FindBy(css = ".wrapper ul.left >li > a > span")
    private List<WebElement> navMenu;

    public LeftMenuComponent(WebDriver driver) {
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
}

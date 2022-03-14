package com.epam.tc.hw3.page.objects.composite;

import java.util.List;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DifferentElementsPage extends AbstractJdiBasePage {

    @FindBy(css = "div.checkbox-row label.label-checkbox")
    private List<WebElement> checkBoxes;

    @FindBy(css = "div.checkbox-row label.label-radio")
    private List<WebElement> metalsRadio;

    @FindBy(css = "div.colors select.uui-form-element")
    private WebElement colorsSelect;

    @FindBy(css = "ul.logs li")
    private List<WebElement> logs;

    public DifferentElementsPage(WebDriver driver) {
        super(driver);
    }

    public void selectCheckBoxes(List<String> thingsToSelect) {
        checkBoxes.forEach(checkBox -> {
            if (thingsToSelect.contains(checkBox.getText())) {
                checkBox.click();
            }
        });
    }

    public void selectMetal(String metal) {
        metalsRadio.forEach(radio -> {
            if (Objects.equals(radio.getText(), metal)) {
                radio.click();
            }
        });
    }

    public void selectColor(String color) {
        Select select = new Select(colorsSelect);
        select.selectByVisibleText(color);
    }

    public boolean areCheckBoxesInLogs(List<String> mustBeSelected) {
        boolean checkBoxesNoticed = true;
        for (String checkBox : mustBeSelected) {
            for (WebElement log : logs) {
                String logText = log.getText();
                if (logText.contains(checkBox) && !logText.contains("true")) {
                    checkBoxesNoticed = false;
                }
            }
        }
        return checkBoxesNoticed;
    }

    public boolean isMetalInLogs(String metal) {
        boolean radioNoticed = true;
        for (WebElement log : logs) {
            String logText = log.getText();
            if (logText.contains("metal") && !logText.contains(metal)) {
                radioNoticed = false;
            }
        }
        return radioNoticed;
    }

    public boolean isColorInLogs(String color) {
        boolean dropdownNoticed = true;
        for (WebElement log : logs) {
            String logText = log.getText();
            if (logText.contains("Colors") && !logText.contains(color)) {
                dropdownNoticed = false;
            }
        }
        return dropdownNoticed;
    }
}

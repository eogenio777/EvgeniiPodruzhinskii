package com.epam.tc.hw6.page;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class UserTablePage extends AbstractJdiBasePage {

    @FindBy(css = "#user-table select")
    List<WebElement> dropdowns;

    @FindBy(css = "#user-table a")
    List<WebElement> usernames;

    @FindBy(css = "#user-table span")
    List<WebElement> descriptions;

    @FindBy(css = "#user-table input")
    List<WebElement> checkboxes;

    @FindBy(css = "ul.logs li")
    private List<WebElement> logs;

    public UserTablePage(WebDriver driver) {
        super(driver);
    }

    public long getDropdownsCount() {
        return dropdowns.size();
    }

    public long getUsernamesCount() {
        return usernames.size();
    }

    public long getDescriptionsCount() {
        return descriptions.size();
    }

    public long getCheckboxesCount() {
        return checkboxes.size();
    }

    public List<String> getUsernames() {
        return usernames.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
    }

    public List<String> getDescriptions() {
        return descriptions.stream()
                           .map(WebElement::getText)
                           .collect(Collectors.toList());
    }

    public List<Map<String, String>> getUsersMap() {
        List<Map<String, String>> userInfo = new LinkedList<>();
        for (int i = 0; i < usernames.size(); ++i) {
            HashMap<String, String> tempMap = new HashMap<>();
            tempMap.put("Number", String.valueOf(i + 1));
            tempMap.put("User", usernames.get(i).getText());
            tempMap.put("Description", descriptions.get(i).getText().replaceAll("\\r\\n|\\r|\\n", " "));
            userInfo.add(tempMap);
        }
        return userInfo;
    }

    public List<String> getDropdownForUser(String username) {
        Select select =
            new Select(driver.findElement(By.xpath("//td[a[text()='" + username + "']]/preceding-sibling::td/select")));
        return select.getOptions().stream()
                     .map(WebElement::getText)
                     .collect(Collectors.toList());
    }

    public void selectCheckboxForUser(String username) {
        WebElement checkbox =
            driver.findElement(By.xpath("//td[a[text()='" + username + "']]/following-sibling::td//input"));
        checkbox.click();
    }

    public boolean isSubstringInLogs(String substring) {
        for (WebElement log : logs) {
            String logText = log.getText();
            if (logText.contains(substring)) {
                return true;
            }
        }
        return false;
    }
}

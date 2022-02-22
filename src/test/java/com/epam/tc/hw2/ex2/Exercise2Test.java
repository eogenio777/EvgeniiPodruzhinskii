package com.epam.tc.hw2.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.BaseExerciseTest;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Exercise2Test extends BaseExerciseTest {

    protected final String metal = "Selen";
    protected final String color = "Yellow";
    protected final List<String> checkBoxesTexts = Arrays.asList("Water", "Wind");

    @Test
    public void testLoginAndLogs() {
        // 1) Open test site by URL
        driver.get(url);

        // 2) Assert Browser title
        assertThat(driver.getTitle()).isEqualTo("Home Page");

        // 3) Perform login
        WebElement loginForm = driver.findElement(By.id("user-icon"));
        loginForm.click();

        WebElement name = driver.findElement(By.id("name"));
        name.sendKeys(username);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(pass);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // 4) Assert User name in the left-top side of screen that user is logged in
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement userFirstSecondName = driver.findElement(By.id("user-name"));

        // wait until the username is no more hidden
        webDriverWait.until(ExpectedConditions.visibilityOf(userFirstSecondName));

        assertThat(userFirstSecondName.getText()).isEqualTo(usernameInfo);

        // 5) Open through the header menu Service -> Different Elements Page
        WebElement service = driver.findElement(By.cssSelector("li.dropdown > a.dropdown-toggle"));
        service.click();

        WebElement differentElements =
            driver.findElement(By.cssSelector("ul.dropdown-menu a[href=\"different-elements.html\"]"));

        webDriverWait.until(ExpectedConditions.visibilityOf(differentElements));

        differentElements.click();

        // 6) Select checkboxes: Water, Wind
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("div.checkbox-row label.label-checkbox"));
        checkBoxes.forEach(checkBox -> {
            if (checkBoxesTexts.contains(checkBox.getText())) {
                checkBox.click();
            }
        });

        // 7) Select radio: Selen
        List<WebElement> metals = driver.findElements(By.cssSelector("div.checkbox-row label.label-radio"));
        metals.forEach(radio -> {
            if (Objects.equals(radio.getText(), metal)) {
                radio.click();
            }
        });

        // 8) Select in dropdown: Yellow
        Select colors = new Select(driver.findElement(By.cssSelector("div.colors select.uui-form-element")));
        colors.selectByVisibleText(color);

        // 9) Assert that:
        List<WebElement> logs = driver.findElements(By.cssSelector("ul.logs li"));

        //• for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        for (String checkBox : checkBoxesTexts) {
            boolean checkBoxNoticed = false;
            for (WebElement log : logs) {
                String logText = log.getText();
                if (logText.contains(checkBox)) {
                    assertThat(logText.contains("true")).isTrue();
                    checkBoxNoticed = true;
                }
            }
            assertThat(checkBoxNoticed).isTrue();
        }

        //• for radio button there is a log row and value is corresponded to the status of radio button
        boolean radioNoticed = false;
        for (WebElement log : logs) {
            String logText = log.getText();
            if (logText.contains("metal")) {
                assertThat(logText.contains(metal)).isTrue();
                radioNoticed = true;
            }
        }
        assertThat(radioNoticed).isTrue();

        //• for dropdown there is a log row and value is corresponded to the selected value.
        boolean dropdownNoticed = false;
        for (WebElement log : logs) {
            String logText = log.getText();
            if (logText.contains("Colors")) {
                assertThat(logText.contains(color)).isTrue();
                dropdownNoticed = true;
            }
        }
        assertThat(dropdownNoticed).isTrue();


    }
}

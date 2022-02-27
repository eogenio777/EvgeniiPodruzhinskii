package com.epam.tc.hw3.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.BaseExerciseTest;
import com.epam.tc.hw3.page.objects.composite.DifferentElementsPage;
import com.epam.tc.hw3.page.objects.composite.HomePage;
import com.epam.tc.hw3.page.objects.composite.component.FrameComponent;
import com.epam.tc.hw3.utils.DataProviderForHW3;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Exercise2Test extends BaseExerciseTest {

    @Test(
        dataProviderClass = DataProviderForHW3.class,
        dataProvider = "EX2"
    )
    public void testLoginAndLogs(String title, String metal, String color, List<String> checkBoxesTexts) {

        // set up the soft assertions
        FileInputStream fis;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);

            // 1) Open test site by URL
            driver.get(properties.getProperty("url"));

            // initialize homePage
            HomePage homePage = new HomePage(driver);

            // 2) Assert Browser title
            assertThat(homePage.getTitle()).isEqualTo(title);

            // 3) Perform login

            homePage.topMenu().clickLoginForm();
            homePage.topMenu().sendKeysName(properties.getProperty("username"));
            homePage.topMenu().sendKeysPassword(properties.getProperty("password"));
            homePage.topMenu().clickLoginButton();

            // 4) Assert User name in the left-top side of screen that user is logged in
            assertThat(homePage.topMenu().getUserNameInfo())
                .isEqualTo(properties.getProperty("usernameInfo"));

            // 5) Open through the header menu Service -> Different Elements Page
            homePage.topMenu().clickServiceDropdown();

            homePage.topMenu().clickDifferentElementsButton();

            // open different elements page
            DifferentElementsPage differentElementsPage = new DifferentElementsPage(driver);

            // 6) Select checkboxes: Water, Wind
            differentElementsPage.selectCheckBoxes(checkBoxesTexts);

            // 7) Select radio: Selen
            differentElementsPage.selectMetal(metal);

            // 8) Select in dropdown: Yellow
            differentElementsPage.selectColor(color);

            // 9) Assert that:
            //• for each checkbox there is an individual log row and value is corresponded to the status of checkbox
            assertThat(differentElementsPage.areCheckBoxesInLogs(checkBoxesTexts)).isTrue();

            //• for radio button there is a log row and value is corresponded to the status of radio button
            assertThat(differentElementsPage.isMetalInLogs(metal)).isTrue();

            //• for dropdown there is a log row and value is corresponded to the selected value.
            assertThat(differentElementsPage.isColorInLogs(color)).isTrue();

        } catch (IOException e) {
            System.err.println("There is no property file");
        }
    }
}

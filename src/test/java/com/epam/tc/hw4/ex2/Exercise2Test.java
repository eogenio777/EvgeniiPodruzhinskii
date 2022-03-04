package com.epam.tc.hw4.ex2;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.page.objects.composite.DifferentElementsPage;
import com.epam.tc.hw3.page.objects.composite.HomePage;
import com.epam.tc.hw4.BaseExerciseTestHW4;
import com.epam.tc.hw4.storynames.Tags;
import com.epam.tc.hw4.utils.DataProviderForHW4;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import java.util.List;
import org.testng.annotations.Test;

@Feature(Tags.FEATURE_EX2)
public class Exercise2Test extends BaseExerciseTestHW4 {

    @Test(
        dataProviderClass = DataProviderForHW4.class,
        dataProvider = "EX2"
    )
    @Step
    @Description("Exercise 2 test including testing login and logs")
    @Story(Tags.STORY_EX2)
    public void testLoginAndLogs(String title, String metal, String color, List<String> checkBoxesTexts) {

        step("Open test site by URL");
        driver.get(properties.getProperty("url"));

        step("initialize homePage");
        HomePage homePage = new HomePage(driver);

        step("Assert Browser title");
        assertThat(homePage.getTitle()).isEqualTo(title);

        step("Perform login");
        homePage.topMenu().performLogin(properties.getProperty("username"), properties.getProperty("password"));

        step("Assert User name in the left-top side of screen that user is logged in");
        assertThat(homePage.topMenu().getUserNameInfo())
            .isEqualTo(properties.getProperty("usernameInfo"));

        step("Open through the header menu Service -> Different Elements Page");
        homePage.topMenu().clickServiceDropdown();

        homePage.topMenu().clickDifferentElementsButton();

        step("open different elements page");
        DifferentElementsPage differentElementsPage = new DifferentElementsPage(driver);

        step("Select checkboxes: Water, Wind");
        differentElementsPage.selectCheckBoxes(checkBoxesTexts);

        step("Select radio: Selen");
        differentElementsPage.selectMetal(metal);

        step("Select in dropdown: Yellow");
        differentElementsPage.selectColor(color);

        step("Assert that for each checkbox there is an individual log row and value is corresponded "
            + "to the status of checkbox");
        assertThat(differentElementsPage.areCheckBoxesInLogs(checkBoxesTexts)).isTrue();

        step("Assert that for radio button there is a log row and value is corresponded to the status"
            + " of radio button");
        assertThat(differentElementsPage.isMetalInLogs(metal)).isTrue();

        step("Assert that for dropdown there is a log row and value is corresponded to the selected value.");
        assertThat(differentElementsPage.isColorInLogs(color)).isTrue();
    }
}

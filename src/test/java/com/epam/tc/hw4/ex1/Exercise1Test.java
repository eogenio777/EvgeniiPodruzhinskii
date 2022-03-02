package com.epam.tc.hw4.ex1;

import static io.qameta.allure.Allure.step;

import com.epam.tc.hw3.page.objects.composite.HomePage;
import com.epam.tc.hw3.page.objects.composite.component.FrameComponent;
import com.epam.tc.hw4.BaseExerciseTestHW4;
import com.epam.tc.hw4.storynames.Tags;
import com.epam.tc.hw4.utils.DataProviderForHW4;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

@Feature(Tags.FEATURE_EX1)
public class Exercise1Test extends BaseExerciseTestHW4 {

    @Test(
        dataProviderClass = DataProviderForHW4.class,
        dataProvider = "EX1"
    )
    @Step
    @Description("Exercise 1 test including testing login and general page elements")
    @Story(Tags.STORY_EX1)
    public void testLoginAndGeneralHomePageElements(String title, String[] expectedNavBarTitles,
                                                    long iconsCount,
                                                    String[] expectedTextsUnderIcons,
                                                    String[] expectedMenuTitles) {
        step("Open test site by URL");
        driver.get(properties.getProperty("url"));

        // set up the soft assertions
        SoftAssertions softAssertions = new SoftAssertions();

        // initialize homePage
        HomePage homePage = new HomePage(driver);

        step("Assert Browser title");
        softAssertions.assertThat(homePage.getTitle()).isEqualTo(title);

        step("Perform login");
        homePage.topMenu().clickLoginForm();
        homePage.topMenu().sendKeysName(properties.getProperty("username"));
        homePage.topMenu().sendKeysPassword(properties.getProperty("password"));
        homePage.topMenu().clickLoginButton();

        step("Assert Username is logged");
        softAssertions.assertThat(homePage.topMenu().getUserNameInfo())
                      .isEqualTo(properties.getProperty("usernameInfo"));


        step("Assert that there are 4 items on the header section and they have proper texts");
        softAssertions.assertThat(homePage.topMenu().getNavMenuSize()).isEqualTo(expectedNavBarTitles.length);
        assertItemsTexts(homePage.topMenu().getNavMenuTitles(), expectedNavBarTitles, softAssertions);

        step("Assert that there are 4 images on the Index Page");
        softAssertions.assertThat(homePage.getIconsCount()).isEqualTo(iconsCount);


        step("Assert that there are 4 texts on the Index Page under icons and they have proper text");
        softAssertions.assertThat(homePage.getIconsTextsCount()).isEqualTo(expectedTextsUnderIcons.length);
        assertItemsTexts(homePage.getIconsTexts(), expectedTextsUnderIcons, softAssertions);

        step("Assert that there is the iframe with “Frame Button” exist");
        softAssertions.assertThat(homePage.isFrameDisplayed()).isTrue();

        step("Switch to the iframe and check that there is “Frame Button” in the iframe");
        driver.switchTo().frame("frame");
        FrameComponent frameComponent = new FrameComponent(driver);
        softAssertions.assertThat(frameComponent.isFrameButtonDisplayed()).isTrue();

        step("Switch to original window back");
        driver.switchTo().defaultContent();

        step("Assert that there are 5 items in the Left Section are displayed and they have proper text");
        softAssertions.assertThat(homePage.leftMenu().getNavMenuSize()).isEqualTo(expectedMenuTitles.length);
        assertItemsTexts(homePage.leftMenu().getNavMenuTitles(), expectedMenuTitles, softAssertions);

        step("Assert all");
        softAssertions.assertAll();
    }

    public void assertItemsTexts(List<String> actualItemsTexts,
                                 String[] expectedTexts, SoftAssertions softAssertions) {

        for (int i = 0; i < actualItemsTexts.size(); ++i) {
            softAssertions.assertThat(actualItemsTexts.get(i)).isEqualTo(expectedTexts[i]);
        }
    }
}

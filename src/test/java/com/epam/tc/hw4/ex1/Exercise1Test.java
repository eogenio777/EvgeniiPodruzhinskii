package com.epam.tc.hw4.ex1;

import com.epam.tc.hw4.BaseExerciseTestHW4;
import com.epam.tc.hw4.storynames.Tags;
import com.epam.tc.hw4.utils.DataProviderForHW4;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

@Feature(Tags.FEATURE_EX1)
public class Exercise1Test extends BaseExerciseTestHW4 {

    @Test(
        dataProviderClass = DataProviderForHW4.class,
        dataProvider = "EX1"
    )
    @Description("Exercise 1 test including testing login and general page elements")
    @Story(Tags.STORY_EX1)
    public void testLoginAndGeneralHomePageElements(String title, String[] expectedNavBarTitles,
                                                    long iconsCount,
                                                    String[] expectedTextsUnderIcons,
                                                    String[] expectedMenuTitles) {
        actionStep.openSite();

        // set up the soft assertions
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actionStep.getTitle()).isEqualTo(title);

        actionStep.performLogin(properties.getProperty("username"), properties.getProperty("password"));

        softAssertions.assertThat(actionStep.getUserNameInfo())
                      .isEqualTo(properties.getProperty("usernameInfo"));

        softAssertions.assertThat(actionStep.getHeaderMenuSize()).isEqualTo(expectedNavBarTitles.length);
        assertItemsTexts(actionStep.getHeaderMenuTitles(), expectedNavBarTitles, softAssertions);

        softAssertions.assertThat(actionStep.getIconsCount()).isEqualTo(iconsCount);

        softAssertions.assertThat(actionStep.getIconsTextsCount()).isEqualTo(expectedTextsUnderIcons.length);
        assertItemsTexts(actionStep.getIconsTexts(), expectedTextsUnderIcons, softAssertions);

        softAssertions.assertThat(actionStep.isFrameDisplayed()).isTrue();

        softAssertions.assertThat(actionStep.isFrameButtonDisplayed()).isTrue();

        actionStep.switchToDefault();

        softAssertions.assertThat(actionStep.getLeftMenuSize()).isEqualTo(expectedMenuTitles.length);
        assertItemsTexts(actionStep.getLeftMenuTitles(), expectedMenuTitles, softAssertions);

        softAssertions.assertAll();
    }
}

package com.epam.tc.hw4.ex2;

import static org.assertj.core.api.Assertions.assertThat;

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

        actionStep.openSite();

        assertThat(actionStep.getTitle()).isEqualTo(title);

        actionStep.performLogin(properties.getProperty("username"), properties.getProperty("password"));

        assertThat(actionStep.getUserNameInfo())
            .isEqualTo(properties.getProperty("usernameInfo"));

        actionStep.openDifferentElements();

        actionStep.selectCheckBoxes(checkBoxesTexts);

        actionStep.selectMetal(metal);

        actionStep.selectColor(color);

        assertThat(actionStep.areCheckBoxesInLogs(checkBoxesTexts)).isTrue();

        assertThat(actionStep.isMetalInLogs(metal)).isTrue();

        assertThat(actionStep.isColorInLogs(color)).isTrue();
    }
}

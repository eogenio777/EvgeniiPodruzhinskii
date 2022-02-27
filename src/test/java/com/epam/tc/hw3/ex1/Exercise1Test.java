package com.epam.tc.hw3.ex1;

import com.epam.tc.hw2.BaseExerciseTest;
import com.epam.tc.hw3.page.objects.composite.HomePage;
import com.epam.tc.hw3.page.objects.composite.component.FrameComponent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Exercise1Test extends BaseExerciseTest {

    private final String[] expectedNavBarTitles = new String[] {"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"};

    private final String[] expectedTextsUnderIcons =
        new String[] {"To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable", "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"};

    private final String[] expectedMenuTitles =
        new String[] {"Home", "Contact form", "Service", "Metals & Colors", "Elements packs"};

    @Test
    public void testLoginAndGeneralHomePageElements() {
        // set up the soft assertions
        SoftAssertions softAssertions = new SoftAssertions();
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
            softAssertions.assertThat(homePage.getTitle()).isEqualTo("Home Page");

            // 3) Perform login

            homePage.topMenu().clickLoginForm();
            homePage.topMenu().sendKeysName(properties.getProperty("username"));
            homePage.topMenu().sendKeysPassword(properties.getProperty("password"));
            homePage.topMenu().clickLoginButton();

            // 4) Assert Username is logged
            softAssertions.assertThat(homePage.topMenu().getUserNameInfo())
                          .isEqualTo(properties.getProperty("usernameInfo"));

            // 5) Assert that there are 4 items on the header
            // section and they have proper texts
            softAssertions.assertThat(homePage.topMenu().getNavMenuSize()).isEqualTo(4);
            assertItemsTexts(homePage.topMenu().getNavMenuTitles(), expectedNavBarTitles, softAssertions);


            // 6) Assert that there are 4 images on the Index Page
            softAssertions.assertThat(homePage.getIconsCount()).isEqualTo(4);

            // 7) Assert that there are 4 texts on the
            // Index Page under icons and they have proper text
            softAssertions.assertThat(homePage.getIconsTextsCount()).isEqualTo(4);
            assertItemsTexts(homePage.getIconsTexts(), expectedTextsUnderIcons, softAssertions);

            // 8) Assert that there is the iframe with “Frame Button” exist
            softAssertions.assertThat(homePage.isFrameDisplayed()).isTrue();

            // 9) Switch to the iframe and check that there is “Frame Button” in the iframe
            driver.switchTo().frame("frame");
            FrameComponent frameComponent = new FrameComponent(driver);
            softAssertions.assertThat(frameComponent.isFrameButtonDisplayed()).isTrue();

            // 10) Switch to original window back
            driver.switchTo().defaultContent();

            // 11) Assert that there are 5 items in the Left Section
            // are displayed and they have proper text
            softAssertions.assertThat(homePage.leftMenu().getNavMenuSize()).isEqualTo(5);
            assertItemsTexts(homePage.leftMenu().getNavMenuTitles(), expectedMenuTitles, softAssertions);


            softAssertions.assertAll();
        } catch (IOException e) {
            System.err.println("There is no property file");
        }
    }

    public void assertItemsTexts(List<String> actualItemsTexts,
                                 String[] expectedTexts, SoftAssertions softAssertions) {

        for (int i = 0; i < actualItemsTexts.size(); ++i) {
            softAssertions.assertThat(actualItemsTexts.get(i)).isEqualTo(expectedTexts[i]);
        }
    }
}

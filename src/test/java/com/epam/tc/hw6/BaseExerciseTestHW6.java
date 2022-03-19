package com.epam.tc.hw6;

import com.epam.tc.hw6.service.webdriver.WebDriverProvider;
import com.epam.tc.hw6.step.ActionStep;
import io.qameta.allure.Description;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseExerciseTestHW6 {
    protected WebDriver driver;

    protected Properties properties;

    protected ActionStep actionStep;

    @BeforeClass
    @Description("")
    public void readProps() {
        FileInputStream fis;
        properties = new Properties();
        String configPath = "src/test/resources/config.properties";
        try {
            fis = new FileInputStream(configPath);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Property file " + configPath + " is not found", e);
        }
    }

    @BeforeMethod
    public void setUp(ITestContext context) {
        // set up the chrome driver

        driver = WebDriverProvider.getDriver();
        context.setAttribute("driver", driver);

        actionStep = new ActionStep(driver);

        // maximize the window
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        //close all tabs and processes
        driver.quit();
    }

    protected void assertItemsTexts(List<String> actualItemsTexts,
                                 String[] expectedTexts, SoftAssertions softAssertions) {

        for (int i = 0; i < actualItemsTexts.size(); ++i) {
            softAssertions.assertThat(actualItemsTexts.get(i)).isEqualTo(expectedTexts[i]);
        }
    }
}

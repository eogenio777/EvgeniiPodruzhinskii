package com.epam.tc.hw4;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseExerciseTestHW4 {
    protected WebDriver driver;

    protected Properties properties;

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
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        context.setAttribute("driver", driver);

        // maximize the window
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        //close all tabs and processes
        driver.quit();
    }
}

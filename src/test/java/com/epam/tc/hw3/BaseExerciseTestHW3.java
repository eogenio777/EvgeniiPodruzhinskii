package com.epam.tc.hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseExerciseTestHW3 {
    protected WebDriver driver;

    protected Properties properties;

    @BeforeClass
    public void readProps() {
        FileInputStream fis;
        properties = new Properties();
        try {
            fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUp() {
        // set up the chrome driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // maximize the window
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        //close all tabs and processes
        driver.quit();
    }
}

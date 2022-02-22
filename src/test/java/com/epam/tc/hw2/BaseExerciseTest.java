package com.epam.tc.hw2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseExerciseTest {
    protected WebDriver driver;
    protected final String url = "https://jdi-testing.github.io/jdi-light/index.html";
    protected final String username = "Roman";
    protected final String pass = "Jdi1234";
    protected final String usernameInfo = "ROMAN IOVLEV";

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

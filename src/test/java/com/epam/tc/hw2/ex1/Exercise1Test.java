package com.epam.tc.hw2.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.BaseExerciseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise1Test extends BaseExerciseTest {

    private final String[] expectedTextsUnderIcons =
        new String[] {
            "To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"};

    private final String[] expectedMenuTitles =
        new String[] {
            "Home",
            "Contact form",
            "Service",
            "Metals & Colors",
            "Elements packs"};

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

    @Test
    public void testLoginAndGeneralHomePageElements() {
        // set up the soft assertions
        SoftAssertions softAssertions = new SoftAssertions();

        // 1) Open test site by URL
        driver.get(url);

        // 2) Assert Browser title
        softAssertions.assertThat(driver.getTitle()).isEqualTo("Home Page");

        // 3) Perform login
        WebElement loginForm = driver.findElement(By.id("user-icon"));
        loginForm.click();

        WebElement name = driver.findElement(By.id("name"));
        name.sendKeys(username);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(pass);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // 4) Assert Username is logged
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement userFirstSecondName = driver.findElement(By.id("user-name"));

        // wait until the username is no more hidden
        webDriverWait.until(ExpectedConditions.visibilityOf(userFirstSecondName));

        softAssertions.assertThat(userFirstSecondName.getText()).isEqualTo(usernameInfo);

        // 5) Assert that there are 4 items on the header -------------------------------------
        // section are displayed and they have proper texts
        WebElement home = driver.findElement(By.cssSelector("header a[href=\"index.html\"]"));
        softAssertions.assertThat(home.isDisplayed()).isTrue();
        softAssertions.assertThat(home.getText()).isEqualTo("HOME");

        WebElement contactForm = driver.findElement(By.cssSelector("header a[href=\"contacts.html\"]"));
        softAssertions.assertThat(contactForm.isDisplayed()).isTrue();
        softAssertions.assertThat(contactForm.getText()).isEqualTo("CONTACT FORM");

        WebElement service = driver.findElement(By.cssSelector("header a.dropdown-toggle"));
        softAssertions.assertThat(service.isDisplayed()).isTrue();
        softAssertions.assertThat(service.getText()).isEqualTo("SERVICE");

        WebElement metalAndColors = driver.findElement(By.cssSelector("header a[href=\"metals-colors.html\"]"));
        softAssertions.assertThat(metalAndColors.isDisplayed()).isTrue();
        softAssertions.assertThat(metalAndColors.getText()).isEqualTo("METALS & COLORS");

        // 6) Assert that there are 4 images on the
        // Index Page and they are displayed
        List<WebElement> icons = driver.findElements(By.className("icons-benefit"));
        softAssertions.assertThat(icons.size()).isEqualTo(4);
        icons.forEach(icon -> softAssertions.assertThat(icon.isDisplayed()).isTrue());

        // 7) Assert that there are 4 texts on the
        // Index Page under icons and they have proper text
        List<WebElement> textsUnderImages = driver.findElements(By.className("benefit-txt"));
        softAssertions.assertThat(textsUnderImages.size()).isEqualTo(4);
        textsUnderImages.forEach(element -> softAssertions.assertThat(element.isDisplayed()).isTrue());

        List<WebElement> actualTexts = new ArrayList<>(textsUnderImages);
        for (int i = 0; i < actualTexts.size(); ++i) {
            softAssertions.assertThat(actualTexts.get(i).getText()).isEqualTo(expectedTextsUnderIcons[i]);
        }

        // 8) Assert that there is the iframe with “Frame Button” exist
        WebElement frame = driver.findElement(By.id("frame"));
        softAssertions.assertThat(frame).isNotNull();

        // 9) Switch to the iframe and check that there is “Frame Button” in the iframe

        // remembering of the main window
        String windowHandle = driver.getWindowHandle();

        driver.switchTo().frame("frame");
        WebElement frameButton = driver.findElement(By.id("frame-button"));
        softAssertions.assertThat(frameButton).isNotNull();

        // 10) Switch to original window back
        driver.switchTo().window(windowHandle);

        // 11) Assert that there are 5 items in the Left Section
        // are displayed and they have proper text
        List<WebElement> actualLeftItems = driver.findElements(By.cssSelector(".wrapper ul.left >li > a > span"));
        softAssertions.assertThat(actualLeftItems.size()).isEqualTo(5);
        textsUnderImages.forEach(element -> softAssertions.assertThat(element.isDisplayed()).isTrue());

        List<WebElement> actualLeftTexts = new ArrayList<>(actualLeftItems);
        for (int i = 0; i < actualLeftTexts.size(); ++i) {
            softAssertions.assertThat(actualLeftTexts.get(i).getText()).isEqualTo(expectedMenuTitles[i]);
        }


        softAssertions.assertAll();
    }
}

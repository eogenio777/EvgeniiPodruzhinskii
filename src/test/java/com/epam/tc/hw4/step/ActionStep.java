package com.epam.tc.hw4.step;

import com.epam.tc.hw3.page.objects.composite.DifferentElementsPage;
import com.epam.tc.hw3.page.objects.composite.HomePage;
import com.epam.tc.hw3.page.objects.composite.component.FrameComponent;
import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class ActionStep extends AbstractStep {

    private HomePage homePage;
    private DifferentElementsPage differentElementsPage;

    public ActionStep(WebDriver driver) {
        super(driver);
    }

    @Step("Open test site by URL")
    public void openSite() {
        driver.get(properties.getProperty("url"));
        homePage = new HomePage(driver);
    }

    @Step("Get page title")
    public String getTitle() {
        return homePage.getTitle();
    }

    @Step("Perform login")
    public void performLogin(String username, String password) {
        homePage.topMenu().clickLoginForm();
        homePage.topMenu().sendKeysName(username);
        homePage.topMenu().sendKeysPassword(password);
        homePage.topMenu().clickLoginButton();
    }

    @Step("Get logged Username")
    public String getUserNameInfo() {
        return homePage.topMenu().getUserNameInfo();
    }

    @Step("Get number of header items")
    public long getHeaderMenuSize() {
        return homePage.topMenu().getNavMenuSize();
    }

    @Step("Get number of left menu items")
    public long getLeftMenuSize() {
        return homePage.leftMenu().getNavMenuSize();
    }

    @Step("Get number of icons")
    public long getIconsCount() {
        return homePage.getIconsCount();
    }

    @Step("Get number of texts below icons")
    public long getIconsTextsCount() {
        return homePage.getIconsTextsCount();
    }

    @Step("Get header items texts")
    public List<String> getHeaderMenuTitles() {
        return homePage.topMenu().getNavMenuTitles();
    }

    @Step("Get left menu items texts")
    public List<String> getLeftMenuTitles() {
        return homePage.leftMenu().getNavMenuTitles();
    }

    @Step("Get icons texts")
    public List<String> getIconsTexts() {
        return homePage.getIconsTexts();
    }

    @Step("Get frame with 'Frame Button'")
    public boolean isFrameDisplayed() {
        return homePage.isFrameDisplayed();
    }

    @Step("Switch to the iframe and check that there is “Frame Button” in the iframe")
    public boolean isFrameButtonDisplayed() {
        driver.switchTo().frame("frame");
        FrameComponent frameComponent = new FrameComponent(driver);
        return frameComponent.isFrameButtonDisplayed();
    }

    @Step("Switch to original window")
    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    @Step("Open through the header menu Service -> Different Elements Page")
    public void openDifferentElements() {
        homePage.topMenu().clickServiceDropdown();
        homePage.topMenu().clickDifferentElementsButton();
        differentElementsPage = new DifferentElementsPage(driver);
    }

    @Step("Select checkboxes")
    public void selectCheckBoxes(List<String> checkBoxesTexts) {
        differentElementsPage.selectCheckBoxes(checkBoxesTexts);
    }

    @Step("Select radio")
    public void selectMetal(String metal) {
        differentElementsPage.selectMetal(metal);
    }

    @Step("Select in dropdown")
    public void selectColor(String color) {
        differentElementsPage.selectColor(color);
    }

    @Step(
        "Check that for each checkbox there is an individual log row and value is "
            + "corresponded to the status of checkbox")
    public boolean areCheckBoxesInLogs(List<String> checkBoxesTexts) {
        return differentElementsPage.areCheckBoxesInLogs(checkBoxesTexts);
    }

    @Step(
        "Check that for radio button there is a log row and value is corresponded "
            + "to the status of radio button")
    public boolean isMetalInLogs(String metal) {
        return differentElementsPage.isMetalInLogs(metal);
    }

    @Step(
        "Check that for dropdown there is a log row and value is corresponded to the selected value.")
    public boolean isColorInLogs(String color) {
        return differentElementsPage.isColorInLogs(color);
    }
}

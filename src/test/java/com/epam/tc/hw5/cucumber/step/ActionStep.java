package com.epam.tc.hw5.cucumber.step;

import com.epam.tc.hw5.cucumber.context.TestContext;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ActionStep extends AbstractStep {

    @Given("I open JDI GitHub site")
    public void openSite() {
        homePage.open("index.html");
    }


    @Given("I perform login with username {string} and password {string}")
    public void performLogin(String username, String password) {
        homePage.topMenu().clickLoginForm();
        homePage.topMenu().sendKeysName(username);
        homePage.topMenu().sendKeysPassword(password);
        homePage.topMenu().clickLoginButton();
    }


    @Given("I click on 'Service' button in Header")
    public void clickService() {
        homePage.topMenu().clickServiceDropdown();
    }

    @Given("I click on 'Different Elements' button in Service dropdown")
    public void openDifferentElements() {
        homePage.topMenu().clickDifferentElementsButton();
    }

    @When("I click on 'User Table' button in Service dropdown")
    public void openUserTable() {
        homePage.topMenu().clickUserTableButton();
    }

    @ParameterType(value = "'(.*)'")
    public List<String> checkBoxTexts(String s) {
        return Arrays.stream(s.split(","))
                     .map(String::trim)
                     .collect(Collectors.toList());
    }

    @When("I select checkboxes {checkBoxTexts}")
    public void selectCheckBoxes(List<String> checkBoxesTexts) {
        differentElementsPage.selectCheckBoxes(checkBoxesTexts);
        TestContext.getInstance().setObject("checkBoxTexts", checkBoxesTexts);
    }

    @When("I select radio {string}")
    public void selectMetal(String metal) {
        differentElementsPage.selectMetal(metal);
        TestContext.getInstance().setObject("metal", metal);
    }

    @When("I select in dropdown {string}")
    public void selectColor(String color) {
        differentElementsPage.selectColor(color);
        TestContext.getInstance().setObject("color", color);
    }

    @When("I select 'vip' checkbox for {string}")
    public void selectCheckbox(String username) {
        userTablePage.selectCheckboxForUser(username);
    }
}

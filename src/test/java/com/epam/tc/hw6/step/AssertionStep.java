package com.epam.tc.hw6.step;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw5.cucumber.context.TestContext;
import com.epam.tc.hw5.cucumber.step.AbstractStep;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssertionStep extends AbstractStep {
    @Then(
        "for each checkbox there is an individual log row and value is corresponded to the status of checkbox")
    public void assertCheckBoxesInLogs() {
        List<String> checkBoxTexts = TestContext.getInstance().getObject("checkBoxTexts", List.class);
        assertThat(differentElementsPage.areCheckBoxesInLogs(checkBoxTexts)).isTrue();
    }

    @Then(
        "for radio button there is a log row and value is corresponded to the status of radio button")
    public void assertMetalInLogs() {
        String metal = TestContext.getInstance().getObject("metal", String.class);
        assertThat(differentElementsPage.isMetalInLogs(metal)).isTrue();
    }

    @Then(
        "for dropdown there is a log row and value is corresponded to the selected value")
    public void assertColorInLogs() {
        String color = TestContext.getInstance().getObject("color", String.class);
        assertThat(differentElementsPage.isColorInLogs(color)).isTrue();
    }

    @Then("{string} page should be opened")
    public void assertUserTableIsOpened(String title) {
        assertThat(userTablePage.getTitle()).isEqualTo(title);
    }

    @Then("{int} Number Type Dropdowns should be displayed on Users Table on User Table Page")
    public void assertNumberOfDropdowns(long count) {
        assertThat(userTablePage.getDropdownsCount()).isEqualTo(count);
    }

    @Then("{int} Usernames should be displayed on Users Table on User Table Page")
    public void assertNumberOfUsernames(long count) {
        assertThat(userTablePage.getUsernamesCount()).isEqualTo(count);
    }

    @Then("{int} Description texts under images should be displayed on Users Table on User Table Page")
    public void assertNumberOfDescriptions(long count) {
        assertThat(userTablePage.getDescriptionsCount()).isEqualTo(count);
    }

    @Then("{int} checkboxes should be displayed on Users Table on User Table Page")
    public void assertNumberOfCheckboxes(long count) {
        assertThat(userTablePage.getCheckboxesCount()).isEqualTo(count);
    }

    @Then("User table should contain following values:")
    public void assertDataTable(DataTable table) {
        List<Map<String, String>> expectedUsers = table.asMaps();

        List<Map<String, String>> actualUsers = userTablePage.getUsersMap();

        for (int i = 0; i < expectedUsers.size(); ++i) {
            assertThat(actualUsers.get(i).get("Number"))
                .isEqualTo(expectedUsers.get(i).get("Number"));
            assertThat(actualUsers.get(i).get("User"))
                .isEqualTo(expectedUsers.get(i).get("User"));
            assertThat(actualUsers.get(i).get("Description"))
                .isEqualTo(expectedUsers.get(i).get("Description"));
        }
    }

    @Then("droplist should contain values in column Type for user {string}")
    public void assertDropdown(String user, DataTable table) {
        List<String> expectedStatuses = table.asList()
                                             .stream()
                                             .skip(1)
                                             .collect(Collectors.toList());
        List<String> actualStatuses = userTablePage.getDropdownForUser(user);
        for (int i = 0; i < expectedStatuses.size(); ++i) {
            assertThat(actualStatuses.get(i)).isEqualTo(expectedStatuses.get(i));
        }
    }

    @Then("1 log row has {string} text in log section")
    public void assertCheckboxLogs(String logSubstring) {
        assertThat(userTablePage.isSubstringInLogs(logSubstring)).isTrue();
    }
}

package com.epam.tc.hw5.cucumber.step;

import com.epam.tc.hw3.page.objects.composite.DifferentElementsPage;
import com.epam.tc.hw3.page.objects.composite.HomePage;
import com.epam.tc.hw3.page.objects.composite.UserTablePage;
import com.epam.tc.hw5.cucumber.context.TestContext;
import org.openqa.selenium.WebDriver;

public class AbstractStep {

    protected HomePage homePage;
    protected DifferentElementsPage differentElementsPage;
    protected UserTablePage userTablePage;

    protected AbstractStep() {
        WebDriver driver = TestContext.getInstance().getObject("driver", WebDriver.class);
        homePage = new HomePage(driver);
        differentElementsPage = new DifferentElementsPage(driver);
        userTablePage = new UserTablePage(driver);
    }
}

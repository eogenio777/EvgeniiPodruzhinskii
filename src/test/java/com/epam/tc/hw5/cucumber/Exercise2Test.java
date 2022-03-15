package com.epam.tc.hw5.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
                 features = {"classpath:/com/epam/tc/hw5/features/ex2.feature"})
public class Exercise2Test extends AbstractTestNGCucumberTests {
}

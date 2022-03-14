package com.epam.tc.hw5.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"classpath:/com/epam/tc/hw5/features/ex2.feature"})
public class Exercise2Test extends AbstractTestNGCucumberTests {
}

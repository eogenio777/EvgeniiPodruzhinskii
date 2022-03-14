package com.epam.tc.hw5.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"classpath:/com/epam/tc/hw5/features/ex3.feature"})
public class Exercise3Test extends AbstractTestNGCucumberTests {
}

package com.epam.tc.hw6.step;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;

public class AbstractStep {

    protected WebDriver driver;
    protected Properties properties;

    protected AbstractStep(WebDriver driver) {
        this.driver = driver;
        FileInputStream fis;
        properties = new Properties();
        String configPath = "src/test/resources/config.properties";
        try {
            fis = new FileInputStream(configPath);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Property file " + configPath + " is not found", e);
        }
    }
}

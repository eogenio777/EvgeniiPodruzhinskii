package com.epam.tc.hw6.service.exeption;

public class WebDriverLaunchTypeExeption extends IllegalArgumentException {
    public WebDriverLaunchTypeExeption() {
        super("Unsupported launch type. Use local or remote.");
    }
}

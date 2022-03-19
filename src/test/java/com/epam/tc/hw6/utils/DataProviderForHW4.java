package com.epam.tc.hw6.utils;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.DataProvider;

public class DataProviderForHW4 {

    private static final String title = "Home Page";

    private static final String[] expectedNavBarTitles =
        new String[] {"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"};

    private static final String[] expectedTextsUnderIcons =
        new String[] {"To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable", "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"};

    private static final String[] expectedMenuTitles =
        new String[] {"Home", "Contact form", "Service", "Metals & Colors", "Elements packs"};

    private static final List<String> checkBoxesTexts = Arrays.asList("Water", "Wind");


    @DataProvider(name = "EX1")
    public static Object[][] getTestDataForEX1() {
        return new Object[][] {
            {title, expectedNavBarTitles, 4, expectedTextsUnderIcons, expectedMenuTitles},
        };
    }

    @DataProvider(name = "EX2")
    public static Object[][] getTestDataForEX2() {
        return new Object[][] {
            {title, "Selen", "Yellow", checkBoxesTexts},
        };
    }
}

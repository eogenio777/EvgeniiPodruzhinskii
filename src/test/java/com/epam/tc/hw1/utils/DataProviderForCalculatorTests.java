package com.epam.tc.hw1.utils;

import org.testng.annotations.DataProvider;

public class DataProviderForCalculatorTests {
    @DataProvider(name = "sum data")
    public static Object[][] getTestDataForSum() {
        return new Object[][] {
            {2, 2, 4},
            {0, 3, 3},
            {-30, 75, 45}
        };
    }

    @DataProvider(name = "sub data")
    public static Object[][] getTestDataForSub() {
        return new Object[][] {
            {2, 2, 0},
            {0, 3, -3},
            {-30, 75, -105}
        };
    }

    @DataProvider(name = "mult data")
    public static Object[][] getTestDataForMult() {
        return new Object[][] {
            {2, 2, 4},
            {0, 3, 0},
            {-30, 75, -2250},
            {13, 1, 13}
        };
    }

    @DataProvider(name = "div data")
    public static Object[][] getTestDataForDiv() {
        return new Object[][] {
            {2, 2, 1},
            {0, 3, 0},
            {12, 3, 4}
        };
    }

    @DataProvider(name = "zero div data")
    public static Object[][] getTestDataForDivByZero() {
        return new Object[][] {
            {2, 0},
            {0, 0},
            {-1, 0}
        };
    }
}

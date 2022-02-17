package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tat.module4.Calculator;
import com.epam.tc.hw1.utils.DataProviderForCalculatorTests;
import org.testng.annotations.Test;

public class CalculatorSubtractTests extends CalculatorTests {

    @Test(
        dataProviderClass = DataProviderForCalculatorTests.class,
        dataProvider = "sub data",
        groups = {"Subtract Test"}
    )
    public void defaultSubTest(long a, long b, long expected) {
        long actual = calculator.sub(a, b);
        assertThat(actual).isEqualTo(expected);
    }
}

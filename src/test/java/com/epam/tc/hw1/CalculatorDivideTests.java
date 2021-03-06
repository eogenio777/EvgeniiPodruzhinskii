package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.epam.tc.hw1.utils.DataProviderForCalculatorTests;
import org.testng.annotations.Test;

public class CalculatorDivideTests extends CalculatorTests {
    @Test(
        dataProviderClass = DataProviderForCalculatorTests.class,
        dataProvider = "div data",
        groups = {"Divide Test"}
    )
    public void defaultDivTest(long a, long b, long expected) {
        long actual = calculator.div(a, b);
        assertThat(actual).isEqualTo(expected);
    }

    @Test(
        dataProviderClass = DataProviderForCalculatorTests.class,
        dataProvider = "zero div data",
        groups = {"Divide Test"}
    )
    public void zeroDivTest(long a, long b) {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> {
            calculator.div(a, b);
        });
    }
}

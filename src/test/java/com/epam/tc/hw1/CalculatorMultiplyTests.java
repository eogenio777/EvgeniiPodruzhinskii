package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tat.module4.Calculator;
import com.epam.tc.hw1.utils.DataProviderForCalculatorTests;
import org.testng.annotations.Test;

public class CalculatorMultiplyTests extends CalculatorTests {

    @Test(
        dataProviderClass = DataProviderForCalculatorTests.class,
        dataProvider = "mult data",
        groups = {"Multiply Test"}
    )
    public void defaultMultTest(long a, long b, long expected) {
        long actual = calculator.mult(a, b);
        assertThat(actual).isEqualTo(expected);
    }
}

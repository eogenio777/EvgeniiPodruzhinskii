package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw1.utils.DataProviderForCalculatorTests;
import org.testng.annotations.Test;

public class CalculatorAddTests extends CalculatorTests {

    @Test(
        dataProviderClass = DataProviderForCalculatorTests.class,
        dataProvider = "sum data",
        groups = {"Add Test"}
        )
    public void defaultSumTest(long a, long b, long expected) {
        long actual = calculator.sum(a, b);
        assertThat(actual).isEqualTo(expected);
    }
}

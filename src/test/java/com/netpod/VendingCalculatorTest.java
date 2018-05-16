package com.netpod;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VendingCalculatorTest {

    private VendingCalculator calculator = new VendingCalculator();

    @Test
    public void shouldCalculateRemainderOfTenCombinationOfThree() {
        int basePoint = 10;
        int remainder = 1;
        List<Integer> sequence = Arrays.asList(5, 5, remainder);

        assertThat(remainder, is(calculator.compute(basePoint, sequence)));
    }

    @Test
    public void shouldCalculateRemainderOfTenWithElevenInSequence() {
        int basePoint = 10;
        int remainder = 1;

        List<Integer> sequence = Arrays.asList(5, remainder, 1, 1, 1, 1, 1);
        assertThat(remainder, is(calculator.compute(basePoint, sequence)));
    }

    @Test
    public void shouldCalculateRemainderOfSeventyFiveWithElevenInSequence() {
        int basePoint = 75;
        int remainder = 31;
        List<Integer> sequence = Arrays.asList(20, 5, 1, 1, 20, 1, 1, 1, 5, 20, remainder);

        assertThat(calculator.compute(basePoint, sequence), is(remainder));
    }

    @Test
    public void shouldCalculateNoRemainder() {
        int basePoint = 75;
        List<Integer> sequence = Arrays.asList(20, 5, 1, 1, 20, 1, 1, 1, 5, 20);

        assertThat(calculator.compute(basePoint, sequence), is(0));
    }

    @Test
    public void shouldCalculateForInsuffientTotalOfSequence() {
        int basePoint = 75;
        List<Integer> sequence = Arrays.asList(20, 5, 1, 1, 20, 1, 1, 1, 5);

        assertThat(calculator.compute(basePoint, sequence), is(-20));
    }

    @Test
    public void shouldCalculateChangeForOverPayment() {
        int basePoint = 10;
        List<Integer> sequence = Arrays.asList(20);

        assertThat(calculator.compute(basePoint, sequence), is(10));
    }
}
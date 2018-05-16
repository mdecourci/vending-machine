package com.netpod;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;

public class VendingCoinsTest {

    public static final Set<Integer> ALLOWED_COINS =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 5, 20, 50, 100)));

    private VendingCoins vendingCoins = new VendingCoins(ALLOWED_COINS);

    @Test
    public void shouldHaveAcceptedCoins() {
        assertThat(vendingCoins.getAcceptedCoins(), containsInAnyOrder(ALLOWED_COINS.toArray()));
    }

    @Test
    public void shouldBeEmptyWithDefaultCreation() {
        VendingCoins vendingCoins = new VendingCoins();
        assertThat(vendingCoins.getAcceptedCoins(), empty());
    }

    @Test
    public void shouldBeEmptyWithNoCoins() {
        VendingCoins vendingCoins = new VendingCoins(Collections.emptySet());
        assertThat(vendingCoins.getAcceptedCoins(), empty());
    }
}
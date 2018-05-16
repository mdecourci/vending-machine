package com.netpod;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PaymentTest {

    public static final Set<Integer> ALLOWED_COINS =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 5, 20, 50, 100)));

    private Payment payment;
    private VendingCoins vendingCoins;

    @Before
    public void setUp() throws Exception {
        vendingCoins = new VendingCoins(ALLOWED_COINS);
        payment = new Payment(Arrays.asList(1, 5, 5, 20), vendingCoins);
    }

    @Test
    public void shouldCreatePaymentWithAmount() {
        assertThat(payment.getAmount(), containsInAnyOrder(Arrays.asList(1, 5, 5, 20).toArray()));
    }

    @Test
    public void shouldBeValid() {
        assertThat(payment.isValid(), is(true));
    }

    @Test
    public void shouldBeInvalidWithIncorrectAmount() {
        payment = new Payment(Arrays.asList(1, 2, 5, 20, 7), vendingCoins);
        assertThat(payment.isValid(), is(false));
    }

    @Test
    public void shouldBeInvalidWithNoPayment() {
        payment = new Payment(Collections.emptyList(), vendingCoins);
        assertThat(payment.isValid(), is(false));
    }

    @Test
    public void shouldBeInvalidWithNoCoins() {
        payment = new Payment(Arrays.asList(1, 5, 5, 20), new VendingCoins());
        assertThat(payment.isValid(), is(false));
    }
}
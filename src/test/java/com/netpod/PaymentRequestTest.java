package com.netpod;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PaymentRequestTest {

    public static final Set<Integer> ALLOWED_COINS =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 5, 20, 50, 100)));

    private PaymentRequest paymentRequest = new PaymentRequest();

    @Test
    public void shouldAddPayment() {
        Payment payment = new Payment();
        paymentRequest.add(payment);

        assertThat(paymentRequest.getPayment(), is(payment));
    }

    @Test
    public void shouldBeEmptyWithNoPayment() {
        paymentRequest.add(null);

        assertThat(paymentRequest.isEmpty(), is(true));
    }

    @Test
    public void shouldBeValidForAValidPayment() {
        Payment payment = new Payment(Arrays.asList(1, 5), new VendingCoins(ALLOWED_COINS));
        paymentRequest.add(payment);

        assertThat(paymentRequest.isValid(), is(true));
    }

    @Test
    public void shouldBeEmptyWhenCleared() {
        Payment payment = new Payment();
        paymentRequest.add(payment);

        paymentRequest.clear();

        assertThat(paymentRequest.isEmpty(), is(true));
    }
}
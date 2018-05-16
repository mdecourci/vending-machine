package com.netpod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineTest {

    public static final Set<Integer> ALLOWED_COINS =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 5, 20, 50, 100)));

    @Mock
    private VendingCalculator calculator;

    @Mock
    private PaymentFactory paymentFactory;

    @Mock
    private PaymentRequest paymentRequest;

    @Mock
    private VendingCoins vendingCoins;

    @InjectMocks
    private VendingMachine vendingMachine = new VendingMachine(paymentFactory, calculator, paymentRequest);

    @Before
    public void setUp() throws Exception {
        vendingMachine.acceptedCoins(vendingCoins);
    }

    @After
    public void tearDown() throws Exception {
        reset(paymentFactory);
        reset(calculator);
    }

    @Test
    public void shouldHaveAcceptedCoins() {
        when(vendingCoins.getAcceptedCoins()).thenReturn(ALLOWED_COINS);
        assertThat(vendingMachine.acceptedCoins(), containsInAnyOrder(1, 5, 20, 50, 100));
    }

    @Test
    public void shouldPay() {
        List<Integer> coins = Arrays.asList(5, 5, 3);

        Payment payment = new Payment(coins, vendingCoins);

        when(paymentFactory.create(eq(Role.CONSUMER), eq(coins), eq(vendingCoins))).thenReturn(payment);

        vendingMachine.payment(Role.CONSUMER, coins);

        verify(paymentRequest).add(eq(payment));
        assertThat(vendingMachine.hasPayment(), is(true));
    }

    @Test
    public void shouldOrderProductWithChange() {
        int change = 3;
        List<Integer> amount = Arrays.asList(5, 5, 1);

        Payment mockPayment = Mockito.mock(Payment.class);

        when(mockPayment.getAmount()).thenReturn(amount);
        when(paymentRequest.getPayment()).thenReturn(mockPayment);
        when(paymentRequest.isEmpty()).thenReturn(false);
        when(paymentRequest.isValid()).thenReturn(true);

        when(calculator.compute(eq(Product.CANDY.getPrice()), eq(amount))).thenReturn(change);

        Order order = vendingMachine.orderProduct(Role.CONSUMER, Product.CANDY);

        assertThat(order.getProduct(), is(Product.CANDY));
        assertThat(order.getChange(), is(change));
    }

    @Test
    public void shouldNotOrderProductWithInvalidPayment() {
        int change = 3;
        List<Integer> amount = Arrays.asList(5, 5, 3);

        when(paymentRequest.isEmpty()).thenReturn(false);
        when(paymentRequest.isValid()).thenReturn(false);

        Order order = vendingMachine.orderProduct(Role.CONSUMER, Product.CANDY);
        verifyZeroInteractions(calculator);

        assertThat(order.isEmpty(), is(true));
    }

    @Test
    public void shouldCancelPaymentRequestAndRefund() {
        List<Integer> amount = Arrays.asList(5, 5, 1);

        Payment mockPayment = Mockito.mock(Payment.class);
        when(mockPayment.getAmount()).thenReturn(amount);

        when(paymentRequest.isEmpty()).thenReturn(false);
        when(paymentRequest.getPayment()).thenReturn(mockPayment);

        List<Integer> refundedAmount = vendingMachine.cancel(Role.CONSUMER);
        verify(paymentRequest).clear();

        verifyZeroInteractions(calculator);

        assertThat(refundedAmount, containsInAnyOrder(amount.toArray()));
    }

    @Test
    public void shouldResetForSupplier() {

        vendingMachine.reset(Role.SUPPLIER);

        verify(paymentRequest).clear();
        verifyZeroInteractions(calculator);
    }
}
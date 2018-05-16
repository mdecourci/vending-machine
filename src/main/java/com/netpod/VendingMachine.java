package com.netpod;

import com.netpod.command.Command;
import com.netpod.command.CommandName;
import com.netpod.command.CommandSet;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Vending machine: Application entry point
 *
 */
public class VendingMachine {

    public static final Set<Integer> ALLOWED_COINS =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 5, 20, 50, 100)));

    private static final VendingMachine VENDING_MACHINE =
            new VendingMachine(
                    new PaymentFactory(),
                    new VendingCalculator(),
                    new PaymentRequest()).acceptedCoins(new VendingCoins(ALLOWED_COINS));


    public static void main(String[] args )  {
        Command command = CommandSet.singleton().get(CommandName.USER);

        while (command.getName() != CommandName.EXIT) {
            command.execute(command.options());
            command = command.next();
        }
    }

    private VendingCalculator calculator;
    private PaymentFactory paymentFactory;
    private PaymentRequest paymentRequest;
    private VendingCoins vendingCoins;

    public VendingMachine(final PaymentFactory pPaymentFactory, final VendingCalculator pCalculator, final PaymentRequest pPaymentRequest) {
        this.calculator = pCalculator;
        this.paymentFactory = pPaymentFactory;
        paymentRequest = pPaymentRequest;
    }

    public static VendingMachine singleton() {
        return VENDING_MACHINE;
    }

    public List<Integer> cancel(final Role pRole) {

        if (Role.CONSUMER != pRole || paymentRequest.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> refund = paymentRequest.getPayment().getAmount();
        paymentRequest.clear();

        return refund;
    }

    private static void exit() {

    }

    public VendingMachine acceptedCoins(final VendingCoins pAccepetedVendingCoins) {
        this.vendingCoins = pAccepetedVendingCoins;
        return this;
    }

    public Set<Integer> acceptedCoins() {
        return vendingCoins.getAcceptedCoins();
    }

    public Order orderProduct(final Role pRole, final Product pProduct) {
        if (Role.CONSUMER != pRole || !Product.isValid(pProduct) || paymentRequest.isEmpty() || !paymentRequest.isValid()) {
            return new Order();
        }
        return new Order(pProduct, calculator.compute(pProduct.getPrice(), paymentRequest.getPayment().getAmount()));
    }

    public void payment(final Role pRole, final List<Integer> pAmount) {
        paymentRequest.add(paymentFactory.create(pRole, pAmount, vendingCoins));
    }

    public boolean hasPayment() {
        return !paymentRequest.isEmpty();
    }

    public void reset(final Role pRole) {
        if (Role.SUPPLIER == pRole) {
            paymentRequest.clear();
        }
    }
}

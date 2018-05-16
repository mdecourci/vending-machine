package com.netpod;

import java.util.List;
import java.util.Set;

public class PaymentFactory {
    public Payment create(final Role pRole, final List<Integer> pAmount, final VendingCoins pVendingCoins) {
        if (Role.CONSUMER == pRole) {
            return new Payment(pAmount, pVendingCoins);
        }
        return new Payment();
    }
}

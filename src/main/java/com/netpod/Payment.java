package com.netpod;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Payment {
    private final VendingCoins vendingCoins;
    private final List<Integer> amount;

    public Payment() {
        vendingCoins = new VendingCoins();
        amount = Collections.unmodifiableList(Collections.emptyList());
    }

    public Payment(final List<Integer> pAmount, final VendingCoins pVendingCoins) {
        this.amount = pAmount;
        this.vendingCoins = pVendingCoins;;
    }

    public List<Integer> getAmount() {
        return amount;
    }

    public boolean isValid() {
        if (amount == null || amount.isEmpty() ||  vendingCoins.isEmpty()) {
            return false;
        }
        return amount.stream().allMatch(p -> vendingCoins.getAcceptedCoins().stream().collect(Collectors.toSet()).contains(p));
    }
}

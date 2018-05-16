package com.netpod;

import java.util.Collections;
import java.util.Set;

public class VendingCoins {
    private Set<Integer> acceptedCoins;

    public VendingCoins() {
        acceptedCoins = Collections.unmodifiableSet(Collections.emptySet());
    }

    public VendingCoins(final Set<Integer> pAllowedCoins) {
        acceptedCoins = Collections.unmodifiableSet(pAllowedCoins);
    }

    public Set<Integer> getAcceptedCoins() {
        return acceptedCoins;
    }

    public boolean isEmpty() {
        return acceptedCoins == null || acceptedCoins.isEmpty();
    }
}

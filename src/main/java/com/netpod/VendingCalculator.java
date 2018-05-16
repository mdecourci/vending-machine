package com.netpod;

import java.util.Arrays;
import java.util.List;

public class VendingCalculator {

    public Integer compute(final int pBasePoint, final List<Integer> pCombination) {
        int total = pCombination.stream().reduce(0, Integer::sum);
        int remainder = 0;
        if (total > pBasePoint) {
           remainder = total % pBasePoint;
        }
        if (remainder == 0) {
           remainder = total - pBasePoint;
        }
        return remainder;
    }
}

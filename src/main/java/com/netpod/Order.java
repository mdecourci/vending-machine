package com.netpod;

public class Order {
    private final Product product;
    private final int change;

    public Order() {
        product = null;
        change = 0;
    }

    public Order(final Product pProduct, final int pChange) {
        product = pProduct;
        change = pChange;
    }

    public Product getProduct() {
        return product;
    }

    public int getChange() {
        return change;
    }

    public boolean isEmpty() {
        return (product == null && change == 0);
    }
}

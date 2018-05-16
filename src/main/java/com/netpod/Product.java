package com.netpod;

public enum Product {
    CANDY (1, 10), SNACK (2, 50), NUTS (3, 75), COKE (4, 150), BOTTLE_WATER(5, 100);

    private final int id;
    private final int price;

    Product(final int pId, final int pPrice) {
        this.id = pId;
        this.price = pPrice;
    }

    public int getPrice() {
        return price;
    }

    public static boolean isValid(final Product pProduct) {
        for (Product p : values()) {
            if (p.equals(pProduct)) {
                return true;
            }
        }
        return false;
    }

    public static Product findProduct(final int pId) {
        for (Product p : values()) {
            if (p.id == pId) {
                return p;
            }
        }
        return null;
    }
}

package com.netpod;

public enum Role {
    CONSUMER(1), SUPPLIER(2);

    private final int id;

    private Role(final int pId) {
        this.id = pId;
    }

    public static Role findRole(final int pId) {
        for (Role r : values()) {
            if (r.id == pId) {
                return r;
            }
        }
        return null;
    }
}

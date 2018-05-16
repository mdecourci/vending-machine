package com.netpod;

public class User {
    private static final User USER = new User();
    private Role currentRole;

    public static User singleton() {
        return USER;
    }
    
    public void setRole(final Role pRole) {
        currentRole = pRole;
    }

    public Role getCurrentRole() {
        return currentRole;
    }
}

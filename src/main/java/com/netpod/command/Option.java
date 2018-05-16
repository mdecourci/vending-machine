package com.netpod.command;

public class Option<T> {
    private final T parameter;

    public Option(final T pParameter) {
        this.parameter = pParameter;
    }

    public T getParameter() {
        return parameter;
    }
}

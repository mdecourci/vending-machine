package com.netpod;

public class PaymentRequest {
    private Payment payment;

    public void add(final Payment pPayment) {
        this.payment = pPayment;
    }

    public Payment getPayment() {
        return payment;
    }

    public boolean isEmpty() {
        return payment == null;
    }

    public boolean isValid() {
        return (payment != null && payment.isValid());
    }

    public void clear() {
        payment = null;
    }
}

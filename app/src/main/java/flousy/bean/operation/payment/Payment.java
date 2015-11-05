package flousy.bean.operation.payment;

import flousy.bean.operation.Operation;

/**
 * Created by Samir on 19/06/2015.
 */
public class Payment extends Operation {

    public Payment() { super(); }

    public Payment(double value) {
        super(value);
    }

    @Override
    public String getOperationType() {
        return "PAYMENT";
    }
}

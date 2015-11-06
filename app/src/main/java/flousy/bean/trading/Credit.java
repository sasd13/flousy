package flousy.bean.trading;

import flousy.bean.operation.Payment;

/**
 * Created by Samir on 06/11/2015.
 */
public class Credit extends TrafficOperation {

    public Credit() {
        super(new Payment());
    }

    public Credit(String name, double value) {
        this();

        setName(name);
        setValue(value);
    }

    @Override
    public String getTrafficOperationType() {
        return "CREDIT";
    }
}

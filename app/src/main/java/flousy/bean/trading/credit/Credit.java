package flousy.bean.trading.credit;

import flousy.bean.operation.payment.Payment;
import flousy.bean.trading.TrafficOperation;

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
}

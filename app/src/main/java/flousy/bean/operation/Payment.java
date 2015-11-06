package flousy.bean.operation;

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

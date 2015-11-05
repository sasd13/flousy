package flousy.bean.operation;

import flousy.bean.operation.payment.Payment;
import flousy.bean.operation.spend.Spend;

/**
 * Created by Samir on 05/11/2015.
 */
public class OperationFactory {

    private OperationFactory() {}

    public static IOperation create(String type) throws OperationException {
        if ("PAYMENT".equalsIgnoreCase(type)) {
            return new Payment();
        } else if ("SPEND".equalsIgnoreCase(type)) {
            return new Spend();
        } else {
            throw new OperationException("operation type not allowed");
        }
    }
}

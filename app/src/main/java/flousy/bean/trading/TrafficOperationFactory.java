package flousy.bean.trading;

import flousy.bean.trading.credit.Credit;
import flousy.bean.trading.debit.Debit;

/**
 * Created by Samir on 05/11/2015.
 */
public class TrafficOperationFactory {

    private TrafficOperationFactory() {}

    public static ITrafficOperation create(String type) throws TradingException {
        if ("CREDIT".equalsIgnoreCase(type)) {
            return new Credit();
        } else if ("DEBIT".equalsIgnoreCase(type)) {
            return new Debit();
        } else {
            throw new TradingException("traffic operation type not allowed");
        }
    }
}

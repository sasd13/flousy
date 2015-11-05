package flousy.bean.trading;

/**
 * Created by Samir on 05/11/2015.
 */
public class TradingAccountFactory {

    private TradingAccountFactory() {}

    public static ITradingAccount create(String type) throws TradingException {
        if ("CHECKING".equalsIgnoreCase(type)) {
            return new CheckingTradingAccount();
        } else {
            throw new TradingException("trading account type not allowed");
        }
    }
}

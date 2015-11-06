package flousy.bean.trading;

/**
 * Created by Samir on 04/11/2015.
 */
public class CheckingTradingAccount extends TradingAccount {

    public CheckingTradingAccount() { super(); }

    @Override
    public String getTradingAccountType() {
        return "CHECKING";
    }
}

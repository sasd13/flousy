package flousy.bean.trading;

import flousy.util.List;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListTradingAccounts extends List<ITradingAccount> {

    @Override
    public ITradingAccount get(Object id) {
        for (ITradingAccount tradingAccount : this.list) {
            if (tradingAccount.getId() == (long) id) {
                return tradingAccount;
            }
        }

        return null;
    }
}

package flousy.bean.trading;

import java.util.Observer;

import flousy.bean.user.IAccount;

/**
 * Created by Samir on 05/11/2015.
 */
public interface ITradingAccount extends IAccount, Observer {

    String getTradingAccountType();

    double getSold();

    ListTrafficOperations getListTrafficOperations();

    Diary getDiary();
}

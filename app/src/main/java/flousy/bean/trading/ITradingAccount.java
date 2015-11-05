package flousy.bean.trading;

import java.util.Observer;

import flousy.bean.customer.IAccount;
import flousy.util.Diary;

/**
 * Created by Samir on 05/11/2015.
 */
public interface ITradingAccount extends IAccount, Observer {

    String getTradingAccountType();

    double getSold();

    ListTraffics getListTraffics();

    Diary getDiary();
}

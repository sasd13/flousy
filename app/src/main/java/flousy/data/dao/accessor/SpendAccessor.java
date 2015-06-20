package flousy.data.dao.accessor;

import flousy.content.spend.ListSpends;
import flousy.content.spend.Spend;

/**
 * Created by Samir on 11/06/2015.
 */
public interface SpendAccessor {

    void insertSpend(Spend spend, String spendsAccountId);

    void updateSpend(Spend spend);

    void deleteSpend(Spend spend);

    Spend selectSpend(String spendId);

    ListSpends selectSpendsOfSpendsAccount(String spendsAccountId);
}

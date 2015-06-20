package flousy.data.dao.accessor;

import flousy.content.spend.SpendsAccount;

/**
 * Created by Samir on 11/06/2015.
 */
public interface SpendsAccountAccessor {

    void insertSpendsAccount(SpendsAccount spendsAccount, String clientId);

    void updateSpendsAccount(SpendsAccount spendsAccount);

    void deleteSpendsAccount(SpendsAccount spendsAccount);

    SpendsAccount selectSpendsAccount(String spendsAccountOrClientId);
}

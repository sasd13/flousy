package flousy.bean.user;

import flousy.util.List;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListAccounts extends List<IAccount> {

    @Override
    public IAccount get(Object id) {
        for (IAccount account : this.list) {
            if (account.getId() == (long) id) {
                return account;
            }
        }

        return null;
    }
}

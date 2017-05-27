package com.sasd13.flousy.scope;

import com.sasd13.flousy.bean.Account;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class AccountScope extends Scope {

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;

        setChanged();
        notifyObservers();
    }
}

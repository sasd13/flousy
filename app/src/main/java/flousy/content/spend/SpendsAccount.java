package flousy.content.spend;

import flousy.content.Account;

/**
 * Created by Samir on 19/06/2015.
 */
public class SpendsAccount extends Account {

    private ListSpends listSpends;

    public SpendsAccount() {
        super();
    }

    public SpendsAccount(String id) {
        super(id);
    }

    public ListSpends getListPayments() {
        return this.listSpends;
    }

    public void setListSpends(ListSpends listSpends) {
        this.listSpends = listSpends;
    }
}

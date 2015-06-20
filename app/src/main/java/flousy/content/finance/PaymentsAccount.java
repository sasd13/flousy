package flousy.content.finance;

import flousy.content.Account;

/**
 * Created by Samir on 19/06/2015.
 */
public class PaymentsAccount extends Account {

    private ListPayments listPayments;

    public PaymentsAccount() {}

    public ListPayments getListPayments() {
        return this.listPayments;
    }

    public void setListPayments(ListPayments listPayments) {
        this.listPayments = listPayments;
    }
}

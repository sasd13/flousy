package com.sasd13.flousy.scope;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class CustomerScope extends Scope {

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;

        setChanged();
        notifyObservers();
    }
}

package com.sasd13.flousy.scope;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.user.UserCreate;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class SignScope extends Scope {

    private UserCreate userCreate;
    private Customer customer;

    public UserCreate getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(UserCreate userCreate) {
        this.userCreate = userCreate;

        setChanged();
        notifyObservers();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;

        setChanged();
        notifyObservers();
    }
}

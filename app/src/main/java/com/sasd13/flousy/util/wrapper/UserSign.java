package com.sasd13.flousy.util.wrapper;

import com.sasd13.flousy.model.Customer;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class UserSign {

    private UserCreate userCreate;
    private Customer customer;

    public UserCreate getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(UserCreate userCreate) {
        this.userCreate = userCreate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

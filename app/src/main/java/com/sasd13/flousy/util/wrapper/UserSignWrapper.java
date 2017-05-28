package com.sasd13.flousy.util.wrapper;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.user.UserCreate;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class UserSignWrapper {

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

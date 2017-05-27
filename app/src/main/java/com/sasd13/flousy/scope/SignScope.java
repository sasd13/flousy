package com.sasd13.flousy.scope;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.user.User;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class SignScope extends Scope {

    private User user;
    private Customer customer;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

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

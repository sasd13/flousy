package com.sasd13.flousy.scope;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.user.User;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class HomeScope extends Scope {

    private boolean welcomed;
    private User user;
    private Customer customer;

    public boolean isWelcomed() {
        return welcomed;
    }

    public void setWelcomed(boolean welcomed) {
        this.welcomed = welcomed;

        setChanged();
        notifyObservers();
    }

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

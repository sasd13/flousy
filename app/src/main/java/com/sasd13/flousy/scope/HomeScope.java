package com.sasd13.flousy.scope;

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

    public void clearWelcomed() {
        welcomed = false;
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

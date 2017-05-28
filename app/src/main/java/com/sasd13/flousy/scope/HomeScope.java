package com.sasd13.flousy.scope;

import com.sasd13.flousy.bean.user.User;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class HomeScope extends Scope {

    private boolean welcomed;
    private User user;

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
}

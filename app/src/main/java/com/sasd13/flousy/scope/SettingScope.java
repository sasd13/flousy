package com.sasd13.flousy.scope;

import com.sasd13.flousy.bean.user.User;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class SettingScope extends Scope {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

        setChanged();
        notifyObservers();
    }
}

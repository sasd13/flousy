package com.sasd13.flousy.scope;

import com.sasd13.flousy.bean.user.UserUpdate;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class SettingScope extends Scope {

    private UserUpdate userUpdate;

    public UserUpdate getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(UserUpdate userUpdate) {
        this.userUpdate = userUpdate;

        setChanged();
        notifyObservers();
    }
}

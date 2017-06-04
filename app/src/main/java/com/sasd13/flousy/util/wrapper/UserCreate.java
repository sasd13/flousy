package com.sasd13.flousy.util.wrapper;

import com.sasd13.flousy.model.User;
import com.sasd13.javaex.security.Credential;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class UserCreate {

    private User user;
    private Credential credential;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
}

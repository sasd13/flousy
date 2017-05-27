package com.sasd13.flousy.bean.user;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class UserUpdate {

    private User user;
    private CredentialUpdate credentials;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CredentialUpdate getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialUpdate credentials) {
        this.credentials = credentials;
    }
}

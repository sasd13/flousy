package com.sasd13.flousy.util.wrapper;

import com.sasd13.javaex.security.Credential;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class CredentialUpdate {

    private Credential previous, current;

    public Credential getPrevious() {
        return previous;
    }

    public void setPrevious(Credential previous) {
        this.previous = previous;
    }

    public Credential getCurrent() {
        return current;
    }

    public void setCurrent(Credential current) {
        this.current = current;
    }
}

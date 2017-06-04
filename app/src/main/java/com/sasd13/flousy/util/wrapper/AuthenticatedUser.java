package com.sasd13.flousy.util.wrapper;

import com.sasd13.flousy.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class AuthenticatedUser {

    private User user;
    private Map<String, String> session;

    public AuthenticatedUser(User user) {
        this.user = user;

        buildSession();
    }

    private void buildSession() {
        session = new HashMap<>();

        session.put("start", String.valueOf(new Date()));
    }

    public User getUser() {
        return user;
    }

    public Map<String, String> getSession() {
        return session;
    }
}

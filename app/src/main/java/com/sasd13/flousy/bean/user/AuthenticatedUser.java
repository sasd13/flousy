package com.sasd13.flousy.bean.user;

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
        session = new HashMap<>();

        buildSession();
    }

    private void buildSession() {
        session.put("start", String.valueOf(new Date()));
    }

    public User getUser() {
        return user;
    }

    public Map<String, String> getSession() {
        return session;
    }
}

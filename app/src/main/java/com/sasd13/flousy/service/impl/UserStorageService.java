package com.sasd13.flousy.service.impl;

import com.sasd13.flousy.service.IUserStorageService;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class UserStorageService implements IUserStorageService {

    private User user;

    @Override
    public User read() {
        return user;
    }

    @Override
    public void write(User user) {
        this.user = user;
    }

    @Override
    public void clear() {
        user = null;
    }
}

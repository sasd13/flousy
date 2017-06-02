package com.sasd13.flousy.util.builder;

import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.bean.user.UserCreate;
import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.javaex.security.Credential;

/**
 * Created by ssaidali2 on 02/06/2017.
 */

public class NewUserCreateBuilder implements IBuilder<UserCreate> {

    @Override
    public UserCreate build() {
        UserCreate userCreate = new UserCreate();

        userCreate.setUser(new User());
        userCreate.setCredential(new Credential());

        return userCreate;
    }
}

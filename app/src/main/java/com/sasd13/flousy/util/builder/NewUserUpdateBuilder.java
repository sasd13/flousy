package com.sasd13.flousy.util.builder;

import com.sasd13.flousy.bean.user.CredentialUpdate;
import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.bean.user.UserUpdate;
import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.javaex.security.Credential;

/**
 * Created by ssaidali2 on 02/06/2017.
 */

public class NewUserUpdateBuilder implements IBuilder<UserUpdate> {

    private User user;
    private Credential credential;

    public NewUserUpdateBuilder(User user) {
        this.user = user;
    }

    public NewUserUpdateBuilder(User user, Credential credential) {
        this.user = user;
        this.credential = credential;
    }

    @Override
    public UserUpdate build() {
        UserUpdate userUpdate = new UserUpdate();

        userUpdate.setUser(user);

        CredentialUpdate credentials = new CredentialUpdate();
        credentials.setPrevious(credential);
        userUpdate.setCredentials(credentials);

        return userUpdate;
    }
}

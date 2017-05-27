package com.sasd13.flousy.service.impl;

import com.sasd13.flousy.bean.user.AuthenticatedUser;
import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.dao.IUserDAO;
import com.sasd13.flousy.service.IAuthenticationService;
import com.sasd13.flousy.service.ServiceResult;
import com.sasd13.javaex.security.Credential;

import java.util.Collections;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class AuthenticationService implements IAuthenticationService {

    private IUserDAO userDAO;

    public AuthenticationService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ServiceResult<AuthenticatedUser> logIn(Credential credential) {
        User user = userDAO.find(credential);

        return new ServiceResult<>(
                true,
                Collections.<String, String>emptyMap(),
                new AuthenticatedUser(user)
        );
    }
}

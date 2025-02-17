package com.sasd13.flousy.service.impl;

import com.sasd13.flousy.dao.IUserDAO;
import com.sasd13.flousy.service.IAuthenticationService;
import com.sasd13.flousy.service.ISessionStorageService;
import com.sasd13.flousy.service.IUserStorageService;
import com.sasd13.flousy.service.ServiceResult;
import com.sasd13.javaex.security.Credential;

import java.util.Collections;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class AuthenticationService implements IAuthenticationService {

    private ISessionStorageService sessionStorageService;
    private IUserStorageService userStorageService;
    private IUserDAO userDAO;

    public AuthenticationService(ISessionStorageService sessionStorageService, IUserStorageService userStorageService, IUserDAO userDAO) {
        this.sessionStorageService = sessionStorageService;
        this.userStorageService = userStorageService;
        this.userDAO = userDAO;
    }

    @Override
    public boolean isAuthenticated() {
        return sessionStorageService.getUserID() != null && sessionStorageService.getIntermediary() != null;
    }

    @Override
    public ServiceResult<User> logIn(Credential credential) {
        boolean authenticated = false;
        User user = userDAO.find(credential);

        if (user != null) {
            authenticated = true;

            sessionStorageService.putUserID(user.getUserID());
            sessionStorageService.putIntermediary(user.getIntermediary());
            userStorageService.write(user);
        }

        return new ServiceResult<>(
                authenticated,
                Collections.<String, String>emptyMap(),
                user
        );
    }

    @Override
    public void logOut() {
        sessionStorageService.clear();
        userStorageService.clear();
    }
}

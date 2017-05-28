package com.sasd13.flousy.service;

import com.sasd13.flousy.bean.user.User;
import com.sasd13.javaex.security.Credential;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public interface IAuthenticationService {

    boolean isAuthenticated();

    ServiceResult<User> logIn(Credential credential);

    void logOut();
}

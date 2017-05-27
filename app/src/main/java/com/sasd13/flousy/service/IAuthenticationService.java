package com.sasd13.flousy.service;

import com.sasd13.flousy.bean.user.AuthenticatedUser;
import com.sasd13.javaex.security.Credential;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public interface IAuthenticationService {

    ServiceResult<AuthenticatedUser> logIn(Credential credential);
}

package com.sasd13.flousy.service;

import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.bean.user.UserCreate;
import com.sasd13.flousy.bean.user.UserUpdate;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public interface IUserService {

    ServiceResult<Void> create(UserCreate userCreate);

    ServiceResult<Void> update(UserUpdate userUpdate);

    ServiceResult<User> find(String userID);
}

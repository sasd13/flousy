package com.sasd13.flousy.service.impl;

import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.bean.user.UserCreate;
import com.sasd13.flousy.bean.user.UserUpdate;
import com.sasd13.flousy.dao.IUserDAO;
import com.sasd13.flousy.service.IUserService;
import com.sasd13.flousy.service.ServiceResult;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class UserService implements IUserService {

    private IUserDAO userDAO;

    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ServiceResult<Void> create(UserCreate userCreate) {
        userDAO.create(userCreate);

        return ServiceResult.SUCCESS;
    }

    @Override
    public ServiceResult<Void> update(UserUpdate userUpdate) {
        userDAO.update(userUpdate);

        return ServiceResult.SUCCESS;
    }

    @Override
    public ServiceResult<User> find(String userID) {
        User user = userDAO.find(userID);

        return new ServiceResult<>(
                true,
                user
        );
    }
}

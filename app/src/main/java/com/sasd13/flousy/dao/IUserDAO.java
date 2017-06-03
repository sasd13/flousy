package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.bean.user.UserCreate;
import com.sasd13.flousy.bean.user.UserUpdate;
import com.sasd13.javaex.security.Credential;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public interface IUserDAO {

    String TABLE = "users";
    String COLUMN_ID = "_id";
    String COLUMN_USERNAME = "_username";
    String COLUMN_PASSWORD = "_password";
    String COLUMN_USERID = "_userID";
    String COLUMN_INTERMEDIARY = "_intermediary";
    String COLUMN_EMAIL = "_email";

    long create(UserCreate userCreate);

    void update(UserUpdate userUpdate);

    User find(Credential credential);

    User find(String userID);
}

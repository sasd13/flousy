package flousy.db;

import flousy.beans.core.User;

/**
 * Created by Samir on 11/06/2015.
 */
public interface UserTableAccessor extends TableAccessor<User> {

    String USER_TABLE_NAME = "users";

    String USER_ID = "user_id";
    String USER_FIRSTNAME = "user_firstname";
    String USER_LASTNAME = "user_lastname";
    String USER_EMAIL = "user_email";
    String USER_PASSWORD = "user_password";

    User selectByEmail(String email);

    boolean contains(String email);
}

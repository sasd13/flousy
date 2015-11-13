package flousy.db;

import flousy.beans.User;

public interface UserTableAccessor {

    String USER_TABLE_NAME = "users";

    String USER_FIRSTNAME = "user_firstname";
    String USER_LASTNAME = "user_lastname";
    String USER_EMAIL = "user_email";
    String USER_PASSWORD = "user_password";

    long insert(User user);

    void update(User user);

    void delete(User user);

    User select(String email);

    boolean containsByEmail(String email);
}

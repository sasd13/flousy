package flousy.db;

import flousy.beans.Account;
import flousy.beans.User;

public interface AccountTableAccessor {

    String ACCOUNT_TABLE_NAME = "accounts";

    String ACCOUNT_ID = "account_id";
    String ACCOUNT_DATEOPENING = "account_dateopening";
    String USERS_USER_EMAIL = "users_user_email";

    long insert(Account account, User user);

    void update(Account account);

    void delete(Account account);

    Account select(long id);

    Account selectByUser(String userEmail);
}

package flousy.db;

import flousy.beans.core.Account;
import flousy.beans.core.User;

/**
 * Created by Samir on 11/06/2015.
 */
public interface AccountTableAccessor extends TableAccessor<Account> {

    String ACCOUNT_TABLE_NAME = "accounts";

    String ACCOUNT_ID = "account_id";
    String ACCOUNT_DATEOPENING = "account_dateopening";
    String ACCOUNT_SOLD = "account_sold";
    String USERS_USER_ID = "users_user_id";

    long insert(Account account, User user);

    Account selectByUser(long userId);
}

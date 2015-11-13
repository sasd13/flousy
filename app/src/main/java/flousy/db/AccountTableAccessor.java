package flousy.db;

import flousy.beans.Account;

public interface AccountTableAccessor {

    String ACCOUNT_TABLE_NAME = "accounts";

    String ACCOUNT_ID = "account_id";
    String ACCOUNT_DATEOPENING = "account_dateopening";
    String ACCOUNT_USERFIRSTNAME = "account_userfirstname";
    String ACCOUNT_USERLASTNAME = "account_userlastname";
    String ACCOUNT_USEREMAIL = "account_useremail";
    String ACCOUNT_USERPASSWORD = "account_userpassword";
    String ACCOUNT_CLOSED = "account_closed";

    void insert(Account account);

    void update(Account account);

    Account select(long id);

    Account selectByUserEmail(String userEmail);

    boolean containsByUserEmail(String userEmail);
}
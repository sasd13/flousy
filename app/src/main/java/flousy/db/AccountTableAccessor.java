package flousy.db;

import flousy.bean.Account;

public interface AccountTableAccessor {

    String ACCOUNT_TABLE_NAME = "accounts";

    String ACCOUNT_ID = "account_id";
    String ACCOUNT_DATEOPENING = "account_dateopening";
    String ACCOUNT_USERFIRSTNAME = "account_userfirstname";
    String ACCOUNT_USERLASTNAME = "account_userlastname";
    String ACCOUNT_USEREMAIL = "account_useremail";
    String ACCOUNT_USERPASSWORD = "account_userpassword";
    String ACCOUNT_DELETED = "account_deleted";

    void insert(Account account);

    void update(Account account);

    void delete(long id);

    Account select(long id);

    Account selectByUserEmail(String userEmail);

    boolean containsByUserEmail(String userEmail);
}
package flousy.db;

import flousy.bean.Account;

public interface AccountDAO {

    String ACCOUNT_TABLE_NAME = "accounts";

    String ACCOUNT_ID = "account_id";
    String ACCOUNT_DATEOPENING = "account_dateopening";
    String ACCOUNT_CLOSED = "account_closed";
    String CUSTOMERS_CUSTOMER_ID = "customers_customer_id";

    long insert(Account account);

    void update(Account account);

    void delete(long id);

    Account select(long id);

    Account selectByCustomer(long customerId);
}
package flousy.db.dao;

import java.util.List;

import flousy.bean.Account;

public interface AccountDAO {

    String ACCOUNT_TABLE_NAME = "accounts";

    String ACCOUNT_ID = "account_id";
    String ACCOUNT_DATEOPENING = "account_dateopening";
    String ACCOUNT_CLOSED = "account_closed";
    String CUSTOMERS_CUSTOMER_ID = "customers_customer_id";

    void insert(Account account, long customerId);

    void update(Account account);

    void delete(long id);

    Account select(long id);

    List<Account> selectByCustomer(long customerId);
}
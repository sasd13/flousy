package com.sasd13.flousy.db;

import com.sasd13.flousy.bean.Account;
import com.sasd13.javaex.db.IEntityDAO;

public interface AccountDAO extends IEntityDAO<Account> {

    String TABLE = "accounts";

    String COLUMN_ID = "account_id";
    String COLUMN_NUMBER = "account_number";
    String COLUMN_DATEOPENING = "account_dateopening";
    String COLUMN_CLOSED = "account_closed";
    String COLUMN_CUSTOMER_ID = "account_customer_id";

    Account selectByNumber(long number);

    Account selectByCustomer(long customerId);
}
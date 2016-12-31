package com.sasd13.flousy.db.dao;

import com.sasd13.flousy.bean.Account;
import com.sasd13.javaex.dao.ISession;

public interface ICustomerAccountDAO extends ISession<Account> {

    String TABLE = "customeraccounts";
    String COLUMN_CUSTOMER_ID = "_customer_id";
    String COLUMN_ACCOUNT_ID = "_account_id";
}
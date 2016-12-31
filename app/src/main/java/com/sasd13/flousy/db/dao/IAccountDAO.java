package com.sasd13.flousy.db.dao;

import com.sasd13.flousy.bean.Account;
import com.sasd13.javaex.dao.ISession;

public interface IAccountDAO extends ISession<Account> {

    String TABLE = "accounts";
    String COLUMN_ID = "_id";
    String COLUMN_DATEOPENING = "_dateopening";
    String COLUMN_CUSTOMER_ID = "_customer_id";
}
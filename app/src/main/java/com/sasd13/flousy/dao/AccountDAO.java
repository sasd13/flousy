package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Account;
import com.sasd13.javaex.db.IEntityDAO;

public interface AccountDAO extends IEntityDAO<Account> {

    String TABLE = "accounts";

    String COLUMN_ID = "id";
    String COLUMN_DATEOPENING = "dateopening";
    String COLUMN_CUSTOMER_ID = "customer_id";
}
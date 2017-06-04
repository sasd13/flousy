package com.sasd13.flousy.dao;

import java.util.List;

public interface IAccountDAO {

    String TABLE = "accounts";
    String COLUMN_ID = "_id";
    String COLUMN_CODE = "_code";
    String COLUMN_DATEOPENING = "_dateopening";
    String COLUMN_CUSTOMER = "_customer";

    long create(Account account);

    void update(Account account);

    Account read(String accountID);

    List<Account> readAll(String intermediary);
}
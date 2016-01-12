package com.sasd13.flousy.db;

import com.sasd13.flousy.bean.Transaction;
import com.sasd13.javaex.db.IEntityDAO;

import java.util.List;

public interface TransactionDAO extends IEntityDAO<Transaction> {

    String TABLE = "transactions";

    String COLUMN_ID = "transaction_id";
    String COLUMN_DATEREALIZATION = "transaction_daterealization";
    String COLUMN_TITLE = "transaction_title";
    String COLUMN_VALUE = "transaction_value";
    String COLUMN_ACCOUNT_ID = "transaction_account_id";

    List<Transaction> selectByAccount(long accountId);
}
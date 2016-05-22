package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Transaction;
import com.sasd13.javaex.db.IEntityDAO;

import java.util.List;

public interface TransactionDAO extends IEntityDAO<Transaction> {

    String TABLE = "transactions";

    String COLUMN_ID = "id";
    String COLUMN_DATEREALIZATION = "daterealization";
    String COLUMN_TITLE = "title";
    String COLUMN_AMOUNT = "amount";
    String COLUMN_ACCOUNT_ID = "account_id";
}
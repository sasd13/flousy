package com.sasd13.flousy.db;

import java.util.List;

import com.sasd13.flousy.bean.Transaction;

public interface TransactionDAO {

    String TRANSACTION_TABLE_NAME = "transactions";

    String TRANSACTION_ID = "transaction_id";
    String TRANSACTION_DATEREALIZATION = "transaction_daterealization";
    String TRANSACTION_TITLE = "transaction_title";
    String TRANSACTION_VALUE = "transaction_value";
    String ACCOUNTS_ACCOUNT_ID = "accounts_account_id";

    long insert(Transaction transaction);

    void update(Transaction transaction);

    void delete(long id);

    Transaction select(long id);

    List<Transaction> selectByAccount(long accountId);

    List<Transaction> selectByAccount(long accountId, boolean ascOrderedByDateRealization);
}
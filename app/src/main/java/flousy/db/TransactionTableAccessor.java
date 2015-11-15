package flousy.db;

import java.util.List;

import flousy.bean.Transaction;

public interface TransactionTableAccessor {

    String TRANSACTION_TABLE_NAME = "transactions";

    String TRANSACTION_ID = "transaction_id";
    String TRANSACTION_DATEREALIZATION = "transaction_daterealization";
    String TRANSACTION_TITLE = "transaction_title";
    String TRANSACTION_VALUE = "transaction_value";
    String TRANSACTION_DELETED = "transaction_deleted";
    String ACCOUNTS_ACCOUNT_ID = "accounts_account_id";

    long insert(Transaction transaction, long accountId);

    void update(Transaction transaction);

    void delete(long id);

    Transaction select(long id);

    List<Transaction> selectByAccount(long accountId);

    List<Transaction> selectByAccount(long accountId, boolean ascOrderedByDateRealization);
}
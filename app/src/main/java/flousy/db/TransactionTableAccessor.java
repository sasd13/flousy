package flousy.db;

import java.util.List;

import flousy.beans.Account;
import flousy.beans.Transaction;

public interface TransactionTableAccessor {

    String TRANSACTION_TABLE_NAME = "transactions";

    String TRANSACTION_ID = "transaction_id";
    String TRANSACTION_DATEREALIZATION = "transaction_daterealization";
    String TRANSACTION_TITLE = "transaction_title";
    String TRANSACTION_VALUE = "transaction_value";
    String ACCOUNTS_ACCOUNT_ID = "accounts_account_id";

    long insert(Transaction transaction, Account account);

    void update(Transaction transaction);

    void delete(Transaction transaction);

    Transaction select(long id);

    List<Transaction> selectByAccount(long accountId);

    List<Transaction> selectByAccount(long accountId, boolean ascOrderedByDateRealization);
}

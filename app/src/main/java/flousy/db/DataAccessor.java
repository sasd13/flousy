package flousy.db;

import android.content.Context;

import flousy.beans.Account;
import flousy.beans.Transaction;

public interface DataAccessor {

    String getDBType();

    void init(Context context);

    void insertAccount(Account account);

    void insertTransaction(Transaction transaction, Account account);

    void updateAccount(Account account);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(Transaction transaction);

    Account selectAccount(long id);

    Transaction selectTransaction(long id);

    Account selectAccountByUserEmail(String userEmail);

    boolean containsAccountByUserEmail(String userEmail);

    Account selectAccountWithTransactions(long id);
}
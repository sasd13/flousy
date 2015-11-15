package flousy.db;

import android.content.Context;

import java.util.List;

import flousy.beans.Account;
import flousy.beans.Transaction;

public interface DataAccessor {

    void init(Context context);

    void insertAccount(Account account);

    void updateAccount(Account account);

    Account selectAccount(long id);

    Account selectAccountByUserEmail(String userEmail);

    boolean containsAccountByUserEmail(String userEmail);

    Account selectAccountWithTransactions(long id);

    void insertTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(long id);

    Transaction selectTransaction(long id);

    List<Transaction> selectTransactionsByAccount(long accountId);
}
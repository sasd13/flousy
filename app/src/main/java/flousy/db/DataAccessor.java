package flousy.db;

import android.content.Context;

import java.util.List;

import flousy.beans.Account;
import flousy.beans.Transaction;
import flousy.beans.User;

public interface DataAccessor {

    String getDBType();

    void init(Context context);

    long insertUser(User user);

    long insertAccount(Account account, User user);

    long insertTransaction(Transaction transaction, Account account);

    void updateUser(User user);

    void updateAccount(Account account);

    void updateTransaction(Transaction transaction);

    void deleteUser(User user);

    void deleteAccount(Account account);

    void deleteTransaction(Transaction transaction);

    Account selectAccount(long id);

    Transaction selectTransaction(long id);

    User selectUser(String email);

    boolean containsUserByEmail(String email);

    Account selectAccountByUser(String userEmail);

    Account selectAccountByUserWithTransactions(String userEmail);

    List<Transaction> selectTransactionsByAccount(long accountId);
}

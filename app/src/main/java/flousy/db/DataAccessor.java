package flousy.db;

import android.content.Context;

import flousy.beans.core.Account;
import flousy.beans.core.ListOperations;
import flousy.beans.core.Operation;
import flousy.beans.core.User;

/**
 * Created by Samir on 05/11/2015.
 */
public interface DataAccessor {

    String getDBType();

    void init(Context context);

    long insertUser(User user);

    long insertAccount(Account account, User user);

    long insertOperation(Operation operation, Account account);

    void updateUser(User user);

    void updateAccount(Account account);

    void updateOperation(Operation operation);

    void deleteUser(User user);

    void deleteAccount(Account account);

    void deleteOperation(Operation operation);

    User selectUser(long id);

    Account selectAccount(long id);

    Operation selectOperation(long id);

    User selectUserByEmail(String email);

    boolean containsUserByEmail(String email);

    Account selectAccountByUser(long userId);

    Account selectAccountWithOperations(long id);

    ListOperations selectOperationsByAccount(long accountId);
}

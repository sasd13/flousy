package flousy.db.dao;

import android.content.Context;

import java.util.List;

import flousy.bean.Account;
import flousy.bean.Customer;
import flousy.bean.Transaction;

public interface DAO {

    void init(Context context);

    void insertCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(long id);

    Customer selectCustomer(long id);

    Customer selectCustomerByEmail(String email);

    boolean containsCustomerByEmail(String email);

    void insertAccount(Account account, long customerId);

    void updateAccount(Account account);

    void deleteAccount(long id);

    Account selectAccount(long id);

    Account selectAccountWithTransactions(long id);

    List<Account> selectAccountsByCustomer(long customerId);

    void insertTransaction(Transaction transaction, long accountId);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(long id);

    Transaction selectTransaction(long id);

    List<Transaction> selectTransactionsByAccount(long accountId);
}
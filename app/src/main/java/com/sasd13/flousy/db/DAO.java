package com.sasd13.flousy.db;

import android.content.Context;

import java.util.List;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.Transaction;

public abstract class DAO {

    protected CustomerDAO customerDAO;
    protected AccountDAO accountDAO;
    protected TransactionDAO transactionDAO;

    public abstract void init(Context context);

    public abstract void open();

    public abstract void close();

    public long insertCustomer(Customer customer) {
        long id = customerDAO.insert(customer);
        if (id > 0) {
            customer.setId(id);
        }

        return id;
    }

    public void updateCustomer(Customer customer) {
        customerDAO.update(customer);
    }

    public void deleteCustomer(long id) {
        customerDAO.delete(id);
    }

    public Customer selectCustomer(long id) {
        return customerDAO.select(id);
    }

    public Customer selectCustomerByEmail(String email) {
        return customerDAO.selectByEmail(email);
    }

    public long insertAccount(Account account) {
        long id = accountDAO.insert(account);
        if (id > 0) {
            account.setId(id);
        }

        return id;
    }

    public void updateAccount(Account account) {
        accountDAO.update(account);
    }

    public void deleteAccount(long id) {
        accountDAO.delete(id);
    }

    public Account selectAccount(long id) {
        return accountDAO.select(id);
    }

    public Account selectAccountWithTransactions(long id) {
        Account account = accountDAO.select(id);

        try {
            addTransactionsOfAccount(account);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return account;
    }

    public Account selectAccountByCustomer(long customerId) {
        return accountDAO.selectByCustomer(customerId);
    }

    public Account selectAccountByCustomerWithTransactions(long customerId) {
        Account account = selectAccountByCustomer(customerId);

        try {
            addTransactionsOfAccount(account);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return account;
    }

    public long insertTransaction(Transaction transaction) {
        long id = transactionDAO.insert(transaction);
        if (id > 0) {
            transaction.setId(id);
        }

        return id;
    }

    public void updateTransaction(Transaction transaction) {
        transactionDAO.update(transaction);
    }

    public void deleteTransaction(long id) {
        transactionDAO.delete(id);
    }

    public Transaction selectTransaction(long id) {
        return transactionDAO.select(id);
    }

    public List<Transaction> selectTransactionsByAccount(long accountId) {
        return transactionDAO.selectByAccount(accountId);
    }

    private void addTransactionsOfAccount(Account account) {
        List<Transaction> transactions = transactionDAO.selectByAccount(account.getId());
        for (Transaction transaction : transactions) {
            account.addTransaction(transaction);
        }
    }
}
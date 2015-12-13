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

    protected abstract void open();

    protected abstract void close();

    public long insertCustomer(Customer customer) {
        long id;

        open();

        id = customerDAO.insert(customer);
        if (id > 0) {
            customer.setId(id);
        }

        close();

        return id;
    }

    public void updateCustomer(Customer customer) {
        open();

        customerDAO.update(customer);

        close();
    }

    public void deleteCustomer(long id) {
        open();

        customerDAO.delete(id);

        close();
    }

    public Customer selectCustomer(long id) {
        Customer customer;

        open();

        customer = customerDAO.select(id);

        close();

        return customer;
    }

    public Customer selectCustomerByEmail(String email) {
        Customer customer;

        open();

        customer = customerDAO.selectByEmail(email);

        close();

        return customer;
    }

    public boolean containsCustomerByEmail(String email) {
        boolean contains;

        open();

        contains = customerDAO.containsByEmail(email);

        close();

        return contains;
    }

    public long insertAccount(Account account) {
        long id;

        open();

        id = accountDAO.insert(account);
        if (id > 0) {
            account.setId(id);
        }

        close();

        return id;
    }

    public void updateAccount(Account account) {
        open();

        accountDAO.update(account);

        close();
    }

    public void deleteAccount(long id) {
        open();

        accountDAO.delete(id);

        close();
    }

    public Account selectAccount(long id) {
        Account account;

        open();

        account = accountDAO.select(id);

        close();

        return account;
    }

    public Account selectAccountWithTransactions(long id) {
        Account account;

        open();

        account = accountDAO.select(id);

        try {
            addTransactionsOfAccount(account);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        close();

        return account;
    }

    public Account selectAccountByCustomer(long customerId) {
        Account account;

        open();

        account = accountDAO.selectByCustomer(customerId);

        close();

        return account;
    }

    public Account selectAccountByCustomerWithTransactions(long customerId) {
        Account account = selectAccountByCustomer(customerId);

        open();

        try {
            addTransactionsOfAccount(account);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        close();

        return account;
    }

    public long insertTransaction(Transaction transaction) {
        long id;

        open();

        id = transactionDAO.insert(transaction);
        if (id > 0) {
            transaction.setId(id);
        }

        close();

        return id;
    }

    public void updateTransaction(Transaction transaction) {
        open();

        transactionDAO.update(transaction);

        close();
    }

    public void deleteTransaction(long id) {
        open();

        transactionDAO.delete(id);

        close();
    }

    public Transaction selectTransaction(long id) {
        Transaction transaction;

        open();

        transaction = transactionDAO.select(id);

        close();

        return transaction;
    }

    public List<Transaction> selectTransactionsByAccount(long accountId) {
        List<Transaction> listTransactions;

        open();

        listTransactions = transactionDAO.selectByAccount(accountId);

        close();

        return listTransactions;
    }

    private void addTransactionsOfAccount(Account account) {
        List<Transaction> transactions = transactionDAO.selectByAccount(account.getId());
        for (Transaction transaction : transactions) {
            account.addTransaction(transaction);
        }
    }
}
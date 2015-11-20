package flousy.db.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import flousy.bean.Account;
import flousy.bean.Customer;
import flousy.bean.Transaction;
import flousy.db.dao.DAO;

public class SQLiteDAO implements DAO {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    private SQLiteDAO() {}

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, NOM, null, VERSION);

        customerDAO = new CustomerDAO();
        accountDAO = new AccountDAO();
        transactionDAO = new TransactionDAO();
    }

    private void open() {
        db = dbHandler.getWritableDatabase();

        customerDAO.setDB(db);
        accountDAO.setDB(db);
        transactionDAO.setDB(db);
    }

    private void close() {
        db.close();
    }

    @Override
    public void insertCustomer(Customer customer) {
        open();

        customerDAO.insert(customer);

        close();
    }

    @Override
    public void updateCustomer(Customer customer) {
        open();

        customerDAO.update(customer);

        close();
    }

    @Override
    public void deleteCustomer(long id) {
        open();

        customerDAO.delete(id);

        close();
    }

    @Override
    public Customer selectCustomer(long id) {
        Customer customer;

        open();

        customer = customerDAO.select(id);

        close();

        return customer;
    }

    @Override
    public Customer selectCustomerByEmail(String email) {
        Customer customer;

        open();

        customer = customerDAO.selectByEmail(email);

        close();

        return customer;
    }

    @Override
    public boolean containsCustomerByEmail(String email) {
        boolean contains;

        open();

        contains = customerDAO.containsByEmail(email);

        close();

        return contains;
    }

    @Override
    public void insertAccount(Account account, long customerId) {
        open();

        accountDAO.insert(account, customerId);

        close();
    }

    @Override
    public void updateAccount(Account account) {
        open();

        accountDAO.update(account);

        close();
    }

    @Override
    public void deleteAccount(long id) {
        open();

        accountDAO.delete(id);

        close();
    }

    @Override
    public Account selectAccount(long id) {
        Account account;

        open();

        account = accountDAO.select(id);

        close();

        return account;
    }

    @Override
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

    @Override
    public List<Account> selectAccountsByCustomer(long customerId) {
        List<Account> list;

        open();

        list = accountDAO.selectByCustomer(customerId);

        close();

        return list;
    }

    @Override
    public void insertTransaction(Transaction transaction, long accountId) {
        open();

        long id = transactionDAO.insert(transaction, accountId);
        if (id > 0) {
            transaction.setId(id);
        }

        close();
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        open();

        transactionDAO.update(transaction);

        close();
    }

    @Override
    public void deleteTransaction(long id) {
        open();

        transactionDAO.delete(id);

        close();
    }

    @Override
    public Transaction selectTransaction(long id) {
        Transaction transaction;

        open();

        transaction = transactionDAO.select(id);

        close();

        return transaction;
    }

    @Override
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
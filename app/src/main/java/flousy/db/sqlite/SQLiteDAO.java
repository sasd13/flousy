package flousy.db.sqlite;

import android.content.Context;

import java.util.List;

import flousy.beans.Account;
import flousy.beans.Transaction;
import flousy.db.DataAccessor;

public class SQLiteDAO implements DataAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

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
    public String getDBType() {
        return "SQLITE";
    }

    @Override
    public void init(Context context) {
        SQLiteDBHandler dbHandler = new SQLiteDBHandler(context, NOM, null, VERSION);

        accountDAO = new AccountDAO(dbHandler);
        transactionDAO = new TransactionDAO(dbHandler);
    }

    @Override
    public void insertAccount(Account account) {
        accountDAO.open();

        accountDAO.insert(account);

        accountDAO.close();
    }

    @Override
    public void insertTransaction(Transaction transaction, Account account) {
        transactionDAO.open();

        long id = transactionDAO.insert(transaction, account);
        if (id > 0) {
            transaction.setId(id);
        }

        transactionDAO.close();
    }

    @Override
    public void updateAccount(Account account) {
        accountDAO.open();

        accountDAO.update(account);

        accountDAO.close();
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        transactionDAO.open();

        transactionDAO.update(transaction);

        transactionDAO.close();
    }

    @Override
    public void deleteAccount(Account account) {
        accountDAO.open();

        accountDAO.delete(account);

        accountDAO.close();
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionDAO.open();

        transactionDAO.delete(transaction);

        transactionDAO.close();
    }

    @Override
    public Account selectAccount(long id) {
        Account account = null;

        accountDAO.open();

        account = accountDAO.select(id);

        accountDAO.close();

        return account;
    }

    @Override
    public Transaction selectTransaction(long id) {
        Transaction transaction = null;

        transactionDAO.open();

        transaction = transactionDAO.select(id);

        transactionDAO.close();

        return transaction;
    }

    @Override
    public Account selectAccountByUserEmail(String userEmail) {
        Account account = null;

        accountDAO.open();

        account = accountDAO.selectByUserEmail(userEmail);

        accountDAO.close();

        return account;
    }

    @Override
    public boolean containsAccountByUserEmail(String userEmail) {
        boolean contains = false;

        accountDAO.open();

        contains = accountDAO.containsByUserEmail(userEmail);

        accountDAO.close();

        return contains;
    }

    @Override
    public Account selectAccountWithTransactions(long id) {
        Account account = selectAccount(id);

        try {
            List<Transaction> listTransactions = transactionDAO.selectByAccount(id);
            for (Transaction transaction : listTransactions) {
                account.addTransaction(transaction);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return account;
    }
}
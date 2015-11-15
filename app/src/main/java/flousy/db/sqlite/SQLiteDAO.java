package flousy.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import flousy.bean.Account;
import flousy.bean.Transaction;
import flousy.db.DataAccessor;

public class SQLiteDAO implements DataAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

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

        accountDAO = new AccountDAO();
        transactionDAO = new TransactionDAO();
    }

    private void open() {
        db = dbHandler.getWritableDatabase();

        accountDAO.setDB(db);
        transactionDAO.setDB(db);
    }

    private void close() {
        db.close();
    }

    @Override
    public void insertAccount(Account account) {
        open();

        accountDAO.insert(account);

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
    public Account selectAccountByUserEmail(String userEmail) {
        Account account;

        open();

        account = accountDAO.selectByUserEmail(userEmail);

        close();

        return account;
    }

    @Override
    public boolean containsAccountByUserEmail(String userEmail) {
        boolean contains;

        open();

        contains = accountDAO.containsByUserEmail(userEmail);

        close();

        return contains;
    }

    @Override
    public Account selectAccountWithTransactions(long id) {
        Account account = selectAccount(id);

        open();

        try {
            List<Transaction> listTransactions = selectTransactionsByAccount(account.getId());
            for (Transaction transaction : listTransactions) {
                account.addTransaction(transaction);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        close();

        return account;
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
}
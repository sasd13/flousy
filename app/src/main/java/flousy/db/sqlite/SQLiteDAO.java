package flousy.db.sqlite;

import android.content.Context;

import java.util.List;

import flousy.beans.User;
import flousy.beans.Account;
import flousy.beans.Transaction;
import flousy.db.DataAccessor;

public class SQLiteDAO implements DataAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

    private UserDAO userDAO;
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

        userDAO = new UserDAO(dbHandler);
        accountDAO = new AccountDAO(dbHandler);
        transactionDAO = new TransactionDAO(dbHandler);
    }

    @Override
    public long insertUser(User user) {
        long id = 0;

        userDAO.open();

        id = userDAO.insert(user);

        userDAO.close();

        return id;
    }

    @Override
    public long insertAccount(Account account, User user) {
        long id = 0;

        accountDAO.open();

        id = accountDAO.insert(account, user);
        if (id > 0) {
            account.setId(id);
        }

        accountDAO.close();

        return id;
    }

    @Override
    public long insertTransaction(Transaction transaction, Account account) {
        long id = 0;

        transactionDAO.open();

        id = transactionDAO.insert(transaction, account);
        if (id > 0) {
            transaction.setId(id);
        }

        transactionDAO.close();

        return id;
    }

    @Override
    public void updateUser(User user) {
        userDAO.open();

        userDAO.update(user);

        userDAO.close();
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
    public void deleteUser(User user) {
        userDAO.open();

        userDAO.delete(user);

        userDAO.close();
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
    public User selectUser(String email) {
        User user = null;

        userDAO.open();

        user = userDAO.select(email);

        userDAO.close();

        return user;
    }

    @Override
    public boolean containsUserByEmail(String email) {
        boolean contains = false;

        userDAO.open();

        contains = userDAO.containsByEmail(email);

        userDAO.close();

        return contains;
    }

    @Override
    public Account selectAccountByUser(String userEmail) {
        Account account = null;

        accountDAO.open();

        account = accountDAO.selectByUser(userEmail);

        accountDAO.close();

        return account;
    }

    @Override
    public Account selectAccountByUserWithTransactions(String userEmail) {
        Account account = selectAccountByUser(userEmail);

        try {
            List<Transaction> listTransactions = selectTransactionsByAccount(account.getId());
            for (Transaction transaction : listTransactions) {
                account.getListTransactions().add(transaction);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public List<Transaction> selectTransactionsByAccount(long accountId) {
        List<Transaction> listTransactions = null;

        transactionDAO.open();

        listTransactions = transactionDAO.selectByAccount(accountId);

        transactionDAO.close();

        return listTransactions;
    }
}

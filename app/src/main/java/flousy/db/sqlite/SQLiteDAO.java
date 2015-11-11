package flousy.db.sqlite;

import android.content.Context;

import flousy.beans.core.User;
import flousy.beans.core.Account;
import flousy.beans.core.ListOperations;
import flousy.beans.core.Operation;
import flousy.db.DataAccessor;

public class SQLiteDAO implements DataAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

    private UserDAO userDAO;
    private AccountDAO accountDAO;
    private OperationDAO operationDAO;

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
        operationDAO = new OperationDAO(dbHandler);
    }

    @Override
    public long insertUser(User user) {
        long id = 0;

        userDAO.open();

        id = userDAO.insert(user);
        if (id > 0) {
            user.setId(id);
        }

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
    public long insertOperation(Operation operation, Account account) {
        long id = 0;

        operationDAO.open();

        id = operationDAO.insert(operation, account);
        if (id > 0) {
            operation.setId(id);
        }

        operationDAO.close();

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
    public void updateOperation(Operation operation) {
        operationDAO.open();

        operationDAO.update(operation);

        operationDAO.close();
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
    public void deleteOperation(Operation operation) {
        operationDAO.open();

        operationDAO.delete(operation);

        operationDAO.close();
    }

    @Override
    public User selectUser(long id) {
        User user = null;

        userDAO.open();

        user = userDAO.select(id);

        userDAO.close();

        return user;
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
    public Operation selectOperation(long id) {
        Operation operation = null;

        operationDAO.open();

        operation = operationDAO.select(id);

        operationDAO.close();

        return operation;
    }

    @Override
    public User selectUserByEmail(String email) {
        User user = null;

        userDAO.open();

        user = userDAO.selectByEmail(email);

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
    public Account selectAccountByUser(long userId) {
        Account account = null;

        accountDAO.open();

        account = accountDAO.selectByUser(userId);

        accountDAO.close();

        return account;
    }

    @Override
    public Account selectAccountByUserWithOperations(long userId) {
        Account account = selectAccountByUser(userId);

        try {
            ListOperations listOperations = selectOperationsByAccount(account.getId());
            account.setListOperations(listOperations);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public ListOperations selectOperationsByAccount(long accountId) {
        ListOperations listOperations = null;

        operationDAO.open();

        listOperations = operationDAO.selectByAccount(accountId);

        operationDAO.close();

        return listOperations;
    }
}

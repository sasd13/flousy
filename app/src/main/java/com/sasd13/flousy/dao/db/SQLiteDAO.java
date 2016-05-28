package com.sasd13.flousy.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.androidex.db.ISQLiteDAO;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.dao.AccountDAO;
import com.sasd13.flousy.dao.AccountDeepReader;
import com.sasd13.flousy.dao.CustomerDAO;
import com.sasd13.flousy.dao.OperationDAO;
import com.sasd13.flousy.dao.OperationDeepReader;
import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.javaex.db.ILayeredDAO;
import com.sasd13.javaex.db.ITransactional;

public class SQLiteDAO implements ILayeredDAO, ISQLiteDAO, ITransactional {

    private static SQLiteDAO instance = null;

    private SQLiteDatabaseHandler dbHandler;
    private SQLiteDatabase db;

    protected CustomerDAO customerDAO;
    protected AccountDAO accountDAO;
    protected OperationDAO operationDAO;

    private AccountDeepReader accountDeepReader;
    private OperationDeepReader operationDeepReader;

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    public SQLiteDatabase getDB() {
        return db;
    }

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDatabaseHandler(context, SQLiteDatabaseInfo.DB, null, SQLiteDatabaseInfo.VERSION);

        customerDAO = new SQLiteCustomerDAO();
        accountDAO = new SQLiteAccountDAO();
        operationDAO = new SQLiteOperationDAO();

        accountDeepReader = new AccountDeepReader(accountDAO, customerDAO, operationDAO);
        operationDeepReader = new OperationDeepReader(operationDAO, accountDAO);
    }

    @Override
    public void open() {
        db = dbHandler.getWritableDatabase();

        ((SQLiteEntityDAO) customerDAO).setDB(db);
        ((SQLiteEntityDAO) accountDAO).setDB(db);
        ((SQLiteEntityDAO) operationDAO).setDB(db);
    }

    @Override
    public void close() {
        db.close();
    }

    @Override
    public boolean inTransaction() {
        return db.inTransaction();
    }

    @Override
    public void beginTransaction() throws DAOException {
        db.beginTransaction();
    }

    @Override
    public void endTransaction() {
        db.endTransaction();
    }

    @Override
    public void commit() {
        db.setTransactionSuccessful();
    }

    @Override
    public void rollback() {

    }

    @Override
    public <T> IEntityDAO<T> getEntityDAO(Class<T> mClass) throws DAOException {
        if (Customer.class.equals(mClass)) {
            return (IEntityDAO<T>) customerDAO;
        } else if (Account.class.equals(mClass)) {
            return (IEntityDAO<T>) accountDAO;
        } else if (Operation.class.equals(mClass)) {
            return (IEntityDAO<T>) operationDAO;
        } else {
            return null;
        }
    }

    @Override
    public <T> DeepReader<T> getDeepReader(Class<T> mClass) throws DAOException {
        if (Account.class.equals(mClass)) {
            return (DeepReader<T>) accountDeepReader;
        } else if (Operation.class.equals(mClass)) {
            return (DeepReader<T>) operationDeepReader;
        } else {
            return null;
        }
    }
}
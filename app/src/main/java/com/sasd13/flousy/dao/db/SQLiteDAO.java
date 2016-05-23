package com.sasd13.flousy.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.androidex.db.ISQLiteDAO;
import com.sasd13.flousy.dao.AccountDAO;
import com.sasd13.flousy.dao.AccountDeepReader;
import com.sasd13.flousy.dao.CustomerDAO;
import com.sasd13.flousy.dao.TransactionDAO;
import com.sasd13.flousy.dao.TransactionDeepReader;
import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.javaex.db.ITransactionalLayeredDAO;

public class SQLiteDAO implements ITransactionalLayeredDAO, ISQLiteDAO {

    private static SQLiteDAO instance = null;

    private SQLiteDatabaseHandler dbHandler;
    private SQLiteDatabase db;

    protected CustomerDAO customerDAO;
    protected AccountDAO accountDAO;
    protected TransactionDAO transactionDAO;

    private AccountDeepReader accountDeepReader;
    private TransactionDeepReader transactionDeepReader;

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
        transactionDAO = new SQLiteTransactionDAO();

        accountDeepReader = new AccountDeepReader(accountDAO, customerDAO, transactionDAO);
        transactionDeepReader = new TransactionDeepReader(transactionDAO, accountDAO);
    }

    @Override
    public void open() {
        db = dbHandler.getWritableDatabase();

        ((SQLiteEntityDAO) customerDAO).setDB(db);
        ((SQLiteEntityDAO) accountDAO).setDB(db);
        ((SQLiteEntityDAO) transactionDAO).setDB(db);
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
        if ("Customer".equals(mClass.getSimpleName())) {
            return (IEntityDAO<T>) customerDAO;
        } else if ("Account".equals(mClass.getSimpleName())) {
            return (IEntityDAO<T>) accountDAO;
        } else if ("Transaction".equals(mClass.getSimpleName())) {
            return (IEntityDAO<T>) transactionDAO;
        } else {
            return null;
        }
    }

    @Override
    public <T> DeepReader<T> getDeepReader(Class<T> mClass) throws DAOException {
        if ("Account".equals(mClass.getSimpleName())) {
            return (DeepReader<T>) accountDeepReader;
        } else if ("Transaction".equals(mClass.getSimpleName())) {
            return (DeepReader<T>) transactionDeepReader;
        } else {
            return null;
        }
    }
}
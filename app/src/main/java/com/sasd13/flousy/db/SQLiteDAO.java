package com.sasd13.flousy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.db.dao.IAccountDAO;
import com.sasd13.flousy.db.dao.AccountDeepReader;
import com.sasd13.flousy.db.dao.ICustomerDAO;
import com.sasd13.flousy.db.dao.IOperationDAO;
import com.sasd13.flousy.db.dao.OperationDeepReader;
import com.sasd13.flousy.db.dao.impl.SQLiteAccountDAO;
import com.sasd13.flousy.db.dao.impl.SQLiteCustomerDAO;
import com.sasd13.flousy.db.dao.impl.SQLiteEntityDAO;
import com.sasd13.flousy.db.dao.impl.SQLiteOperationDAO;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.ILayeredDAO;
import com.sasd13.javaex.dao.ISession;

public class SQLiteDAO implements ILayeredDAO, ITransactional {

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    protected ICustomerDAO customerDAO;
    protected IAccountDAO accountDAO;
    protected IOperationDAO operationDAO;

    private AccountDeepReader accountDeepReader;
    private OperationDeepReader operationDeepReader;

    public SQLiteDatabase getDB() {
        return db;
    }

    private SQLiteDAO(Context context) {
        dbHandler = new SQLiteDBHandler(context, SQLiteDBInformation.DB, null, SQLiteDBInformation.VERSION);

        customerDAO = new SQLiteCustomerDAO();
        accountDAO = new SQLiteAccountDAO();
        operationDAO = new SQLiteOperationDAO();

        accountDeepReader = new AccountDeepReader(accountDAO, customerDAO, operationDAO);
        operationDeepReader = new OperationDeepReader(operationDAO, accountDAO);
    }

    public static SQLiteDAO create(Context context) {
        return new SQLiteDAO(context);
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
    public void rollback() {}

    @Override
    public <T> ISession<T> getEntityDAO(Class<T> mClass) {
        if (Customer.class.equals(mClass)) {
            return (ISession<T>) customerDAO;
        } else if (Account.class.equals(mClass)) {
            return (ISession<T>) accountDAO;
        } else if (Operation.class.equals(mClass)) {
            return (ISession<T>) operationDAO;
        } else {
            return null;
        }
    }

    @Override
    public <T> DeepReader<T> getDeepReader(Class<T> mClass) {
        if (Account.class.equals(mClass)) {
            return (DeepReader<T>) accountDeepReader;
        } else if (Operation.class.equals(mClass)) {
            return (DeepReader<T>) operationDeepReader;
        } else {
            return null;
        }
    }
}
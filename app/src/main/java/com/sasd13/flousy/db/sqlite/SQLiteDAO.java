package com.sasd13.flousy.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.androidex.db.ISQLiteDAO;
import com.sasd13.flousy.db.AccountDAO;
import com.sasd13.flousy.db.CustomerDAO;
import com.sasd13.flousy.db.TransactionDAO;
import com.sasd13.javaex.db.IDAO;
import com.sasd13.javaex.db.IEntityDAO;

public class SQLiteDAO implements IDAO, ISQLiteDAO {

    private static SQLiteDAO instance = null;

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    protected CustomerDAO customerDAO;
    protected AccountDAO accountDAO;
    protected TransactionDAO transactionDAO;

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, SQLiteInformation.DB, null, SQLiteInformation.VERSION);

        customerDAO = new SQLiteCustomerDAO();
        accountDAO = new SQLiteAccountDAO();
        transactionDAO = new SQLiteTransactionDAO();
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
    public IEntityDAO getEntityDAO(Class aClass) {
        if ("Customer".equals(aClass.getSimpleName())) {
            return customerDAO;
        } else if ("Account".equals(aClass.getSimpleName())) {
            return accountDAO;
        } else if ("Transaction".equals(aClass.getSimpleName())) {
            return transactionDAO;
        } else {
            return null;
        }
    }
}
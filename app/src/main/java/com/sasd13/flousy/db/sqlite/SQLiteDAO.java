package com.sasd13.flousy.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.flousy.db.DAO;

public class SQLiteDAO extends DAO {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    private SQLiteDAO() { super(); }

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, NOM, null, VERSION);

        customerDAO = new SQLiteCustomerDAO();
        accountDAO = new SQLiteAccountDAO();
        transactionDAO = new SQLiteTransactionDAO();
    }

    @Override
    protected void open() {
        db = dbHandler.getWritableDatabase();

        ((SQLiteCustomerDAO) customerDAO).setDB(db);
        ((SQLiteAccountDAO) accountDAO).setDB(db);
        ((SQLiteTransactionDAO) transactionDAO).setDB(db);
    }

    @Override
    protected void close() {
        db.close();
    }
}
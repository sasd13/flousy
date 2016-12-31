package com.sasd13.flousy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.sasd13.flousy.db.dao.IAccountDAO;
import com.sasd13.flousy.db.dao.ICustomerDAO;
import com.sasd13.flousy.db.dao.IOperationDAO;
import com.sasd13.flousy.db.dao.impl.SQLitePasswordDAO;

public class SQLiteDBHandler extends SQLiteOpenHelper {

    /**
     * Table customers
     */
    public static final String CUSTOMER_TABLE_DROP = "DROP TABLE IF EXISTS " + ICustomerDAO.TABLE + ";";
    public static final String CUSTOMER_TABLE_CREATE = "CREATE TABLE " + ICustomerDAO.TABLE
            + " ("
                + ICustomerDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ICustomerDAO.COLUMN_FIRSTNAME + " TEXT NOT NULL, "
                + ICustomerDAO.COLUMN_LASTNAME + " TEXT NOT NULL, "
                + ICustomerDAO.COLUMN_EMAIL + " TEXT NOT NULL, "
                + ICustomerDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "CONSTRAINT CUSTOMERS_UQ UNIQUE (" + ICustomerDAO.COLUMN_EMAIL + ")"
            + ");";

    /**
     * Table passwords
     */
    public static final String PASSWORD_TABLE_DROP = "DROP TABLE IF EXISTS " + SQLitePasswordDAO.TABLE + ";";
    public static final String PASSWORD_TABLE_CREATE = "CREATE TABLE " + SQLitePasswordDAO.TABLE
            + " ("
                + SQLitePasswordDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLitePasswordDAO.COLUMN_PASSWORD + " TEXT NOT NULL, "
                + SQLitePasswordDAO.COLUMN_CUSTOMER_ID + " INTEGER NOT NULL, "
                + SQLitePasswordDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "FOREIGN KEY (" + SQLitePasswordDAO.COLUMN_CUSTOMER_ID + ") REFERENCES " + ICustomerDAO.TABLE + "(" + ICustomerDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table accounts
     */
    public static final String ACCOUNT_TABLE_DROP = "DROP TABLE IF EXISTS " + IAccountDAO.TABLE + ";";
    public static final String ACCOUNT_TABLE_CREATE = "CREATE TABLE " + IAccountDAO.TABLE
            + " ("
                + IAccountDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IAccountDAO.COLUMN_DATEOPENING + " TEXT NOT NULL, "
                + IAccountDAO.COLUMN_CUSTOMER_ID + " INTEGER NOT NULL, "
                + IAccountDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "FOREIGN KEY (" + IAccountDAO.COLUMN_CUSTOMER_ID + ") REFERENCES " + ICustomerDAO.TABLE + "(" + ICustomerDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table operation
     */
    public static final String OPERATION_TABLE_DROP = "DROP TABLE IF EXISTS " + IOperationDAO.TABLE + ";";
    public static final String OPERATION_TABLE_CREATE = "CREATE TABLE " + IOperationDAO.TABLE
            + " ("
                + IOperationDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IOperationDAO.COLUMN_DATEREALIZATION + " TEXT NOT NULL, "
                + IOperationDAO.COLUMN_TITLE + " TEXT NOT NULL, "
                + IOperationDAO.COLUMN_AMOUNT + " REAL NOT NULL, "
                + IOperationDAO.COLUMN_TYPE + " TEXT NOT NULL, "
                + IOperationDAO.COLUMN_ACCOUNT_ID + " INTEGER NOT NULL, "
                + IOperationDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "FOREIGN KEY (" + IOperationDAO.COLUMN_ACCOUNT_ID + ") REFERENCES " + IAccountDAO.TABLE + "(" + IAccountDAO.COLUMN_ID + ")"
            + ");";


    public SQLiteDBHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CUSTOMER_TABLE_CREATE);
        db.execSQL(PASSWORD_TABLE_CREATE);
        db.execSQL(ACCOUNT_TABLE_CREATE);
        db.execSQL(OPERATION_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(OPERATION_TABLE_DROP);
        db.execSQL(ACCOUNT_TABLE_DROP);
        db.execSQL(PASSWORD_TABLE_DROP);
        db.execSQL(CUSTOMER_TABLE_DROP);

        onCreate(db);
    }

    //Methode pour activer la contrainte de cle etrangere a l'ouverture de la base de donnees
    //Par defaut la contrainte n'est pas active dans SQLite
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}

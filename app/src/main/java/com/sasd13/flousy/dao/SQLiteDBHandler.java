package com.sasd13.flousy.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDBHandler extends SQLiteOpenHelper {

    /**
     * Table users
     */
    private static final String USER_TABLE_DROP = "DROP TABLE IF EXISTS " + IUserDAO.TABLE + ";";
    private static final String USER_TABLE_CREATE = "CREATE TABLE " + IUserDAO.TABLE
            + " ("
            + IUserDAO.COLUMN_USERID + " TEXT NOT NULL PRIMARY KEY, "
            + IUserDAO.COLUMN_USERNAME + " TEXT NOT NULL, "
            + IUserDAO.COLUMN_PASSWORD + " TEXT NOT NULL, "
            + IUserDAO.COLUMN_INTERMEDIARY + " TEXT NOT NULL, "
            + "CONSTRAINT UQ_USR_USERID UNIQUE (" + IUserDAO.COLUMN_USERID + "), "
            + "CONSTRAINT UQ_USR_INTERMEDIARY UNIQUE (" + IUserDAO.COLUMN_INTERMEDIARY + ")"
            + ");";

    /**
     * Table customers
     */
    private static final String CUSTOMER_TABLE_DROP = "DROP TABLE IF EXISTS " + ICustomerDAO.TABLE + ";";
    private static final String CUSTOMER_TABLE_CREATE = "CREATE TABLE " + ICustomerDAO.TABLE
            + " ("
            + ICustomerDAO.COLUMN_CODE + " TEXT NOT NULL PRIMARY KEY, "
            + ICustomerDAO.COLUMN_FIRSTNAME + " TEXT NOT NULL, "
            + ICustomerDAO.COLUMN_LASTNAME + " TEXT NOT NULL, "
            + ICustomerDAO.COLUMN_EMAIL + " TEXT NOT NULL, "
            + "CONSTRAINT UQ_CT UNIQUE (" + ICustomerDAO.COLUMN_CODE + ")"
            + ");";

    /**
     * Table accounts
     */
    private static final String ACCOUNT_TABLE_DROP = "DROP TABLE IF EXISTS " + IAccountDAO.TABLE + ";";
    private static final String ACCOUNT_TABLE_CREATE = "CREATE TABLE " + IAccountDAO.TABLE
            + " ("
            + IAccountDAO.COLUMN_CODE + " TEXT NOT NULL PRIMARY KEY, "
            + IAccountDAO.COLUMN_DATEOPENING + " TEXT NOT NULL, "
            + IAccountDAO.COLUMN_CUSTOMER + " TEXT NOT NULL, "
            + "CONSTRAINT UQ_AC UNIQUE (" + IAccountDAO.COLUMN_CODE + "), "
            + "FOREIGN KEY (" + IAccountDAO.COLUMN_CUSTOMER + ") REFERENCES " + ICustomerDAO.TABLE + "(" + ICustomerDAO.COLUMN_CODE + ")"
            + ");";

    /**
     * Table operation
     */
    private static final String OPERATION_TABLE_DROP = "DROP TABLE IF EXISTS " + IOperationDAO.TABLE + ";";
    private static final String OPERATION_TABLE_CREATE = "CREATE TABLE " + IOperationDAO.TABLE
            + " ("
            + IOperationDAO.COLUMN_CODE + " TEXT NOT NULL PRIMARY KEY, "
            + IOperationDAO.COLUMN_DATEREALIZATION + " TEXT NOT NULL, "
            + IOperationDAO.COLUMN_TITLE + " TEXT NOT NULL, "
            + IOperationDAO.COLUMN_AMOUNT + " REAL NOT NULL, "
            + IOperationDAO.COLUMN_TYPE + " TEXT NOT NULL, "
            + IOperationDAO.COLUMN_ACCOUNT + " TEXT NOT NULL, "
            + IOperationDAO.COLUMN_FLAG_DELETED + " INTEGER NOT NULL DEFAULT 0, "
            + "FOREIGN KEY (" + IOperationDAO.COLUMN_ACCOUNT + ") REFERENCES " + IAccountDAO.TABLE + "(" + IAccountDAO.COLUMN_CODE + ")"
            + ");";


    public SQLiteDBHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(CUSTOMER_TABLE_CREATE);
        db.execSQL(ACCOUNT_TABLE_CREATE);
        db.execSQL(OPERATION_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(OPERATION_TABLE_DROP);
        db.execSQL(ACCOUNT_TABLE_DROP);
        db.execSQL(CUSTOMER_TABLE_DROP);
        db.execSQL(USER_TABLE_DROP);

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

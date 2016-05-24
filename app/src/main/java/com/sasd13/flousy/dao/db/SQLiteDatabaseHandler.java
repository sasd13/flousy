package com.sasd13.flousy.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.sasd13.flousy.dao.AccountDAO;
import com.sasd13.flousy.dao.CustomerDAO;
import com.sasd13.flousy.dao.OperationDAO;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    /**
     * Table customers
     */
    public static final String CUSTOMER_TABLE_DROP = "DROP TABLE IF EXISTS " + CustomerDAO.TABLE + ";";
    public static final String CUSTOMER_TABLE_CREATE = "CREATE TABLE " + CustomerDAO.TABLE
            + " ("
                + CustomerDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CustomerDAO.COLUMN_FIRSTNAME + " TEXT NOT NULL, "
                + CustomerDAO.COLUMN_LASTNAME + " TEXT NOT NULL, "
                + CustomerDAO.COLUMN_EMAIL + " TEXT NOT NULL, "
                + CustomerDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "CONSTRAINT CUSTOMERS_UQ UNIQUE (" + CustomerDAO.COLUMN_EMAIL + ")"
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
                + "FOREIGN KEY (" + SQLitePasswordDAO.COLUMN_CUSTOMER_ID + ") REFERENCES " + CustomerDAO.TABLE + "(" + CustomerDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table accounts
     */
    public static final String ACCOUNT_TABLE_DROP = "DROP TABLE IF EXISTS " + AccountDAO.TABLE + ";";
    public static final String ACCOUNT_TABLE_CREATE = "CREATE TABLE " + AccountDAO.TABLE
            + " ("
                + AccountDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AccountDAO.COLUMN_DATEOPENING + " TEXT NOT NULL, "
                + AccountDAO.COLUMN_CUSTOMER_ID + " INTEGER NOT NULL, "
                + AccountDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "FOREIGN KEY (" + AccountDAO.COLUMN_CUSTOMER_ID + ") REFERENCES " + CustomerDAO.TABLE + "(" + CustomerDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table operation
     */
    public static final String OPERATION_TABLE_DROP = "DROP TABLE IF EXISTS " + OperationDAO.TABLE + ";";
    public static final String OPERATION_TABLE_CREATE = "CREATE TABLE " + OperationDAO.TABLE
            + " ("
                + OperationDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OperationDAO.COLUMN_DATEREALIZATION + " TEXT NOT NULL, "
                + OperationDAO.COLUMN_TITLE + " TEXT NOT NULL, "
                + OperationDAO.COLUMN_AMOUNT + " REAL NOT NULL, "
                + OperationDAO.COLUMN_ACCOUNT_ID + " INTEGER NOT NULL, "
                + OperationDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "FOREIGN KEY (" + OperationDAO.COLUMN_ACCOUNT_ID + ") REFERENCES " + AccountDAO.TABLE + "(" + AccountDAO.COLUMN_ID + ")"
            + ");";


    public SQLiteDatabaseHandler(Context context, String name, CursorFactory factory, int version) {
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

package com.sasd13.flousy.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.sasd13.flousy.db.AccountDAO;
import com.sasd13.flousy.db.CustomerDAO;
import com.sasd13.flousy.db.TransactionDAO;

public class SQLiteDBHandler extends SQLiteOpenHelper {

    /**
     * Table customers
     */
    public static final String CUSTOMER_TABLE_DROP = "DROP TABLE IF EXISTS " + CustomerDAO.TABLE + ";";
    public static final String CUSTOMER_TABLE_CREATE = "CREATE TABLE " + CustomerDAO.TABLE
            + " ("
                + CustomerDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + CustomerDAO.COLUMN_NUMBER + " VARCHAR(255) NOT NULL, "
                + CustomerDAO.COLUMN_FIRSTNAME + " VARCHAR(255) NOT NULL, "
                + CustomerDAO.COLUMN_LASTNAME + " VARCHAR(255) NOT NULL, "
                + CustomerDAO.COLUMN_EMAIL + " VARCHAR(255) NOT NULL, "
                + CustomerDAO.COLUMN_PASSWORD + " VARCHAR(255) NOT NULL, "
                + CustomerDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "PRIMARY KEY (" + CustomerDAO.COLUMN_ID + ")"
                + ", CONSTRAINT CUSTOMERS_UQ UNIQUE (" + CustomerDAO.COLUMN_NUMBER + ")"
            + ");";

    /**
     * Table accounts
     */
    public static final String ACCOUNT_TABLE_DROP = "DROP TABLE IF EXISTS " + AccountDAO.TABLE + ";";
    public static final String ACCOUNT_TABLE_CREATE = "CREATE TABLE " + AccountDAO.TABLE
            + " ("
                + AccountDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + AccountDAO.COLUMN_NUMBER + " VARCHAR(255) NOT NULL, "
                + AccountDAO.COLUMN_DATEOPENING + " VARCHAR(255) NOT NULL, "
                + AccountDAO.COLUMN_CLOSED + " INT NOT NULL, "
                + AccountDAO.COLUMN_CUSTOMER_ID + " INTEGER NOT NULL, "
                + "PRIMARY KEY (" + AccountDAO.COLUMN_ID + ")"
                + ", CONSTRAINT ACCOUNTS_UQ UNIQUE (" + AccountDAO.COLUMN_NUMBER + ")"
                + ", FOREIGN KEY (" + AccountDAO.COLUMN_CUSTOMER_ID + ") REFERENCES " + CustomerDAO.TABLE + "(" + CustomerDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table transactions
     */
    public static final String TRANSACTION_TABLE_DROP = "DROP TABLE IF EXISTS " + TransactionDAO.TABLE + ";";
    public static final String TRANSACTION_TABLE_CREATE = "CREATE TABLE " + TransactionDAO.TABLE
            + " ("
                + TransactionDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + TransactionDAO.COLUMN_DATEREALIZATION + " VARCHAR(255) NOT NULL, "
                + TransactionDAO.COLUMN_TITLE + " VARCHAR(255) NOT NULL, "
                + TransactionDAO.COLUMN_VALUE + " DOUBLE NOT NULL, "
                + TransactionDAO.COLUMN_ACCOUNT_ID + " INTEGER NOT NULL, "
                + "PRIMARY KEY (" + TransactionDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + TransactionDAO.COLUMN_ACCOUNT_ID + ") REFERENCES " + AccountDAO.TABLE + "(" + AccountDAO.COLUMN_ID + ")"
            + ");";


    public SQLiteDBHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CUSTOMER_TABLE_CREATE);
        db.execSQL(ACCOUNT_TABLE_CREATE);
        db.execSQL(TRANSACTION_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TRANSACTION_TABLE_DROP);
        db.execSQL(ACCOUNT_TABLE_DROP);
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

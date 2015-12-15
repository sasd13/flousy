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
    public static final String CUSTOMER_TABLE_DROP = "DROP TABLE IF EXISTS " + CustomerDAO.CUSTOMER_TABLE_NAME + ";";
    public static final String CUSTOMER_TABLE_CREATE =
            "CREATE TABLE " + CustomerDAO.CUSTOMER_TABLE_NAME + " ("
                    + CustomerDAO.CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CustomerDAO.CUSTOMER_FIRSTNAME + " VARCHAR(255) NOT NULL, "
                    + CustomerDAO.CUSTOMER_LASTNAME + " VARCHAR(255) NOT NULL, "
                    + CustomerDAO.CUSTOMER_EMAIL + " VARCHAR(255) NOT NULL, "
                    + CustomerDAO.CUSTOMER_PASSWORD + " VARCHAR(255) NOT NULL);";

    /**
     * Table accounts
     */
    public static final String ACCOUNT_TABLE_DROP = "DROP TABLE IF EXISTS " + AccountDAO.ACCOUNT_TABLE_NAME + ";";
    public static final String ACCOUNT_TABLE_CREATE =
            "CREATE TABLE " + AccountDAO.ACCOUNT_TABLE_NAME + " ("
                    + AccountDAO.ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + AccountDAO.ACCOUNT_DATEOPENING + " VARCHAR(255) NOT NULL, "
                    + AccountDAO.ACCOUNT_CLOSED + " INT NOT NULL, "
                    + AccountDAO.CUSTOMERS_CUSTOMER_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + AccountDAO.CUSTOMERS_CUSTOMER_ID + ") REFERENCES " + CustomerDAO.CUSTOMER_TABLE_NAME + "("+ CustomerDAO.CUSTOMER_ID + "));";

    /**
     * Table transactions
     */
    public static final String TRANSACTION_TABLE_DROP = "DROP TABLE IF EXISTS " + TransactionDAO.TRANSACTION_TABLE_NAME + ";";
    public static final String TRANSACTION_TABLE_CREATE =
            "CREATE TABLE " + TransactionDAO.TRANSACTION_TABLE_NAME + " ("
                    + TransactionDAO.TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TransactionDAO.TRANSACTION_DATEREALIZATION + " VARCHAR(255) NOT NULL, "
                    + TransactionDAO.TRANSACTION_TITLE + " VARCHAR(255) NOT NULL, "
                    + TransactionDAO.TRANSACTION_VALUE + " DOUBLE NOT NULL, "
                    + TransactionDAO.ACCOUNTS_ACCOUNT_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + TransactionDAO.ACCOUNTS_ACCOUNT_ID + ") REFERENCES " + AccountDAO.ACCOUNT_TABLE_NAME + "("+ AccountDAO.ACCOUNT_ID + "));";


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

package flousy.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import flousy.db.AccountTableAccessor;
import flousy.db.CategoryTableAccessor;
import flousy.db.CustomerTableAccessor;
import flousy.db.PaymentTableAccessor;
import flousy.db.ProductTableAccessor;
import flousy.db.SpendTableAccessor;

public class SQLiteDBHandler extends SQLiteOpenHelper {

    /**
     * Table customers
     */
    public static final String CUSTOMER_TABLE_DROP = "DROP TABLE IF EXISTS " + CustomerTableAccessor.CUSTOMER_TABLE_NAME + ";";
    public static final String CUSTOMER_TABLE_CREATE =
            "CREATE TABLE " + CustomerTableAccessor.CUSTOMER_TABLE_NAME + " ("
                    + CustomerTableAccessor.CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CustomerTableAccessor.CUSTOMER_FIRSTNAME + " TEXT NOT NULL, "
                    + CustomerTableAccessor.CUSTOMER_LASTNAME + " TEXT NOT NULL, "
                    + CustomerTableAccessor.CUSTOMER_EMAIL + " TEXT NOT NULL UNIQUE, "
                    + CustomerTableAccessor.CUSTOMER_PASSWORD + " TEXT NOT NULL, "
                    + CustomerTableAccessor.CUSTOMER_PHONE + " TEXT);";

    /**
     * Table accounts
     */
    public static final String ACCOUNT_TABLE_DROP = "DROP TABLE IF EXISTS " + AccountTableAccessor.ACCOUNT_TABLE_NAME + ";";
    public static final String ACCOUNT_TABLE_CREATE =
            "CREATE TABLE " + AccountTableAccessor.ACCOUNT_TABLE_NAME + " ("
                    + AccountTableAccessor.ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + AccountTableAccessor.CUSTOMERS_CUSTOMER_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + AccountTableAccessor.CUSTOMERS_CUSTOMER_ID + ") REFERENCES " + CustomerTableAccessor.CUSTOMER_TABLE_NAME + "("+ CustomerTableAccessor.CUSTOMER_ID + "));";

    /**
     * Table payments
     */
    public static final String PAYMENT_TABLE_DROP = "DROP TABLE IF EXISTS " + PaymentTableAccessor.PAYMENT_TABLE_NAME + ";";
    public static final String PAYMENT_TABLE_CREATE =
            "CREATE TABLE " + PaymentTableAccessor.PAYMENT_TABLE_NAME + " ("
                    + PaymentTableAccessor.PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PaymentTableAccessor.PAYMENT_DATE + " TEXT NOT NULL, "
                    + PaymentTableAccessor.PAYMENT_VALUE + " REAL NOT NULL, "
                    + PaymentTableAccessor.ACCOUNTS_ACCOUNT_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + PaymentTableAccessor.ACCOUNTS_ACCOUNT_ID + ") REFERENCES " + AccountTableAccessor.ACCOUNT_TABLE_NAME + "("+ AccountTableAccessor.ACCOUNT_ID + "));";

    /**
     * Table spends
     */
    public static final String SPEND_TABLE_DROP = "DROP TABLE IF EXISTS " + SpendTableAccessor.SPEND_TABLE_NAME + ";";
    public static final String SPEND_TABLE_CREATE =
            "CREATE TABLE " + SpendTableAccessor.SPEND_TABLE_NAME + " ("
                    + SpendTableAccessor.SPEND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + SpendTableAccessor.SPEND_DATE + " TEXT NOT NULL, "
                    + SpendTableAccessor.SPEND_VALUE + " REAL NOT NULL, "
                    + SpendTableAccessor.ACCOUNTS_ACCOUNT_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + SpendTableAccessor.ACCOUNTS_ACCOUNT_ID + ") REFERENCES " + AccountTableAccessor.ACCOUNT_TABLE_NAME + "("+ AccountTableAccessor.ACCOUNT_ID + "));";

    /**
     * Table categories
     */
    public static final String CATEGORY_TABLE_DROP = "DROP TABLE IF EXISTS " + CategoryTableAccessor.CATEGORY_TABLE_NAME + ";";
    public static final String CATEGORY_TABLE_CREATE =
            "CREATE TABLE " + CategoryTableAccessor.CATEGORY_TABLE_NAME + " ("
                    + CategoryTableAccessor.CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CategoryTableAccessor.CATEGORY_NAME + " TEXT NOT NULL);";

    /**
     * Table products
     */
    public static final String PRODUCT_TABLE_DROP = "DROP TABLE IF EXISTS " + ProductTableAccessor.PRODUCT_TABLE_NAME + ";";
    public static final String PRODUCT_TABLE_CREATE =
            "CREATE TABLE " + ProductTableAccessor.PRODUCT_TABLE_NAME + " ("
                    + ProductTableAccessor.PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ProductTableAccessor.PRODUCT_NAME + " TEXT NOT NULL, "
                    + ProductTableAccessor.PRODUCT_PRICE + " REAL NOT NULL, "
                    + ProductTableAccessor.SPENDS_SPEND_ID + " INTEGER NOT NULL, "
                    + ProductTableAccessor.CATEGORIES_CATEGORY_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + ProductTableAccessor.SPENDS_SPEND_ID + ") REFERENCES " + SpendTableAccessor.SPEND_TABLE_NAME + "("+ SpendTableAccessor.SPEND_ID + "), "
                    + " FOREIGN KEY (" + ProductTableAccessor.CATEGORIES_CATEGORY_ID + ") REFERENCES " + CategoryTableAccessor.CATEGORY_TABLE_NAME + "("+ CategoryTableAccessor.CATEGORY_ID + "));";



    public SQLiteDBHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(CUSTOMER_TABLE_CREATE);
        db.execSQL(ACCOUNT_TABLE_CREATE);
        db.execSQL(PAYMENT_TABLE_CREATE);
        db.execSQL(SPEND_TABLE_CREATE);
        db.execSQL(CATEGORY_TABLE_CREATE);
        db.execSQL(PRODUCT_TABLE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PRODUCT_TABLE_DROP);
        db.execSQL(CATEGORY_TABLE_DROP);
        db.execSQL(SPEND_TABLE_DROP);
        db.execSQL(PAYMENT_TABLE_DROP);
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

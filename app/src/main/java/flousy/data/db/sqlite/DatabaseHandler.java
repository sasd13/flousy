package flousy.data.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Table clients
     */
    public static final String CLIENT_TABLE_DROP = "DROP TABLE IF EXISTS " + ClientDAO.CLIENT_TABLE_NAME + ";";
    public static final String CLIENT_TABLE_CREATE =
            "CREATE TABLE " + ClientDAO.CLIENT_TABLE_NAME + " ("
                    + ClientDAO.CLIENT_ID + " TEXT PRIMARY KEY, "
                    + ClientDAO.CLIENT_FIRSTNAME + " TEXT NOT NULL, "
                    + ClientDAO.CLIENT_LASTNAME + " TEXT NOT NULL, "
                    + ClientDAO.CLIENT_EMAIL + " TEXT NOT NULL UNIQUE, "
                    + ClientDAO.CLIENT_PASSWORD + " TEXT NOT NULL);";

    /**
     * Table phones
     */
    public static final String PHONE_TABLE_DROP = "DROP TABLE IF EXISTS " + PhoneDAO.PHONE_TABLE_NAME + ";";
    public static final String PHONE_TABLE_CREATE =
            "CREATE TABLE " + PhoneDAO.PHONE_TABLE_NAME + " ("
                    + PhoneDAO.PHONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PhoneDAO.PHONE_INDEX + " INTEGER NOT NULL, "
                    + PhoneDAO.PHONE_NUMBER + " TEXT NOT NULL, "
                    + PhoneDAO.PHONE_CLIENT_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + PhoneDAO.PHONE_CLIENT_ID + ") REFERENCES " + ClientDAO.CLIENT_TABLE_NAME + "(" + ClientDAO.CLIENT_ID + "));";

    /**
     * Table incomes
     */
    public static final String INCOME_TABLE_DROP = "DROP TABLE IF EXISTS " + IncomeDAO.INCOME_TABLE_NAME + ";";
    public static final String INCOME_TABLE_CREATE =
            "CREATE TABLE " + IncomeDAO.INCOME_TABLE_NAME + " ("
                    + IncomeDAO.INCOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + IncomeDAO.INCOME_VALUE + " REAL NOT NULL, "
                    + IncomeDAO.INCOME_START_DATE + " INTEGER NOT NULL, "
                    + IncomeDAO.INCOME_END_DATE + " INTEGER, "
                    + IncomeDAO.INCOME_PERIODICITY + " TEXT NOT NULL, "
                    + IncomeDAO.INCOME_CLIENT_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + IncomeDAO.INCOME_CLIENT_ID + ") REFERENCES " + ClientDAO.CLIENT_TABLE_NAME + "(" + ClientDAO.CLIENT_ID + "));";

    /**
     * Table payments_accounts
     */
    public static final String PAYMENTSACCOUNT_TABLE_DROP = "DROP TABLE IF EXISTS " + PaymentsAccountDAO.PAYMENTSACCOUNT_TABLE_NAME + ";";
    public static final String PAYMENTSACCOUNT_TABLE_CREATE =
            "CREATE TABLE " + PaymentsAccountDAO.PAYMENTSACCOUNT_TABLE_NAME + " ("
                    + PaymentsAccountDAO.PAYMENTSACCOUNT_ID + " TEXT PRIMARY KEY, "
                    + PaymentsAccountDAO.PAYMENTSACCOUNT_CLIENT_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + PaymentsAccountDAO.PAYMENTSACCOUNT_CLIENT_ID + ") REFERENCES " + ClientDAO.CLIENT_TABLE_NAME + "(" + ClientDAO.CLIENT_ID + "));";

    /**
     * Table payments
     */
    public static final String PAYMENT_TABLE_DROP = "DROP TABLE IF EXISTS " + PaymentDAO.PAYMENT_TABLE_NAME + ";";
    public static final String PAYMENT_TABLE_CREATE =
            "CREATE TABLE " + PaymentDAO.PAYMENT_TABLE_NAME + " ("
                    + PaymentDAO.PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PaymentDAO.PAYMENT_VALUE + " REAL NOT NULL, "
                    + PaymentDAO.PAYMENT_DATE + " INTEGER NOT NULL, "
                    + PaymentDAO.PAYMENT_ACCOUNT_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + PaymentDAO.PAYMENT_ACCOUNT_ID + ") REFERENCES " + PaymentsAccountDAO.PAYMENTSACCOUNT_TABLE_NAME + "(" + PaymentsAccountDAO.PAYMENTSACCOUNT_ID + "));";

    /**
     * Table spends_accounts
     */
    public static final String SPENDSACCOUNT_TABLE_DROP = "DROP TABLE IF EXISTS " + SpendsAccountDAO.SPENDSACCOUNT_TABLE_NAME + ";";
    public static final String SPENDSACCOUNT_TABLE_CREATE =
            "CREATE TABLE " + SpendsAccountDAO.SPENDSACCOUNT_TABLE_NAME + " ("
                    + SpendsAccountDAO.SPENDSACCOUNT_ID + " TEXT PRIMARY KEY, "
                    + SpendsAccountDAO.SPENDSACCOUNT_CLIENT_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + SpendsAccountDAO.SPENDSACCOUNT_CLIENT_ID + ") REFERENCES " + ClientDAO.CLIENT_TABLE_NAME + "(" + ClientDAO.CLIENT_ID + "));";

    /**
     * Table spends
     */
    public static final String SPEND_TABLE_DROP = "DROP TABLE IF EXISTS " + SpendDAO.SPEND_TABLE_NAME + ";";
    public static final String SPEND_TABLE_CREATE =
            "CREATE TABLE " + SpendDAO.SPEND_TABLE_NAME + " ("
                    + SpendDAO.SPEND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + SpendDAO.SPEND_DATE + " INTEGER NOT NULL, "
                    + SpendDAO.SPEND_ACCOUNT_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + SpendDAO.SPEND_ACCOUNT_ID + ") REFERENCES " + SpendsAccountDAO.SPENDSACCOUNT_TABLE_NAME + "(" + SpendsAccountDAO.SPENDSACCOUNT_ID + "));";

    /**
     * Table articles
     */
    public static final String ARTICLE_TABLE_DROP = "DROP TABLE IF EXISTS " + ArticleDAO.ARTICLE_TABLE_NAME + ";";
    public static final String ARTICLE_TABLE_CREATE =
            "CREATE TABLE " + ArticleDAO.ARTICLE_TABLE_NAME + " ("
                    + ArticleDAO.ARTICLE_ID + " TEXT PRIMARY KEY, "
                    + ArticleDAO.ARTICLE_NAME + " TEXT NOT NULL, "
                    + ArticleDAO.ARTICLE_PRICE + " REAL NOT NULL, "
                    + ArticleDAO.ARTICLE_CATEGORY + " TEXT NOT NULL, "
                    + ArticleDAO.ARTICLE_SPEND_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + ArticleDAO.ARTICLE_SPEND_ID + ") REFERENCES " + SpendDAO.SPEND_TABLE_NAME + "(" + SpendDAO.SPEND_ID + "));";


    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(CLIENT_TABLE_CREATE);
        db.execSQL(PHONE_TABLE_CREATE);
        db.execSQL(INCOME_TABLE_CREATE);
        db.execSQL(PAYMENTSACCOUNT_TABLE_CREATE);
        db.execSQL(PAYMENT_TABLE_CREATE);
        db.execSQL(SPENDSACCOUNT_TABLE_CREATE);
        db.execSQL(SPEND_TABLE_CREATE);
        db.execSQL(ARTICLE_TABLE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ARTICLE_TABLE_DROP);
        db.execSQL(SPEND_TABLE_DROP);
        db.execSQL(SPENDSACCOUNT_TABLE_DROP);
        db.execSQL(PAYMENT_TABLE_DROP);
        db.execSQL(PAYMENTSACCOUNT_TABLE_DROP);
        db.execSQL(INCOME_TABLE_DROP);
        db.execSQL(PHONE_TABLE_DROP);
        db.execSQL(CLIENT_TABLE_DROP);

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

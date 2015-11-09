package flousy.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import flousy.db.AccountTableAccessor;
import flousy.db.UserTableAccessor;
import flousy.db.OperationTableAccessor;

public class SQLiteDBHandler extends SQLiteOpenHelper {

    /**
     * Table users
     */
    public static final String USER_TABLE_DROP = "DROP TABLE IF EXISTS " + UserTableAccessor.USER_TABLE_NAME + ";";
    public static final String USER_TABLE_CREATE =
            "CREATE TABLE " + UserTableAccessor.USER_TABLE_NAME + " ("
                    + UserTableAccessor.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + UserTableAccessor.USER_FIRSTNAME + " TEXT NOT NULL, "
                    + UserTableAccessor.USER_LASTNAME + " TEXT NOT NULL, "
                    + UserTableAccessor.USER_EMAIL + " TEXT NOT NULL UNIQUE, "
                    + UserTableAccessor.USER_PASSWORD + " TEXT NOT NULL);";

    /**
     * Table accounts
     */
    public static final String ACCOUNT_TABLE_DROP = "DROP TABLE IF EXISTS " + AccountTableAccessor.ACCOUNT_TABLE_NAME + ";";
    public static final String ACCOUNT_TABLE_CREATE =
            "CREATE TABLE " + AccountTableAccessor.ACCOUNT_TABLE_NAME + " ("
                    + AccountTableAccessor.ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + AccountTableAccessor.ACCOUNT_DATEOPENING + " TEXT NOT NULL, "
                    + AccountTableAccessor.ACCOUNT_SOLD + " REAL NOT NULL, "
                    + AccountTableAccessor.USERS_USER_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + AccountTableAccessor.USERS_USER_ID + ") REFERENCES " + UserTableAccessor.USER_TABLE_NAME + "("+ UserTableAccessor.USER_ID + "));";

    /**
     * Table operations
     */
    public static final String OPERATION_TABLE_DROP = "DROP TABLE IF EXISTS " + OperationTableAccessor.OPERATION_TABLE_NAME + ";";
    public static final String OPERATION_TABLE_CREATE =
            "CREATE TABLE " + OperationTableAccessor.OPERATION_TABLE_NAME + " ("
                    + OperationTableAccessor.OPERATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + OperationTableAccessor.OPERATION_DATE + " TEXT NOT NULL, "
                    + OperationTableAccessor.OPERATION_TYPE + " TEXT NOT NULL, "
                    + OperationTableAccessor.OPERATION_NAME + " TEXT NOT NULL, "
                    + OperationTableAccessor.OPERATION_VALUE + " REAL NOT NULL, "
                    + OperationTableAccessor.ACCOUNTS_ACCOUNT_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + OperationTableAccessor.ACCOUNTS_ACCOUNT_ID + ") REFERENCES " + AccountTableAccessor.ACCOUNT_TABLE_NAME + "("+ AccountTableAccessor.ACCOUNT_ID + "));";


    public SQLiteDBHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(ACCOUNT_TABLE_CREATE);
        db.execSQL(OPERATION_TABLE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(OPERATION_TABLE_DROP);
        db.execSQL(ACCOUNT_TABLE_DROP);
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

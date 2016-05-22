package com.sasd13.flousy.dao.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.androidex.db.ISQLiteDAO;

public class SQLitePasswordDAO implements ISQLiteDAO {

    public static final String TABLE = "passwords";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CUSTOMER_ID = "customer_id";
    public static final String COLUMN_DELETED = "deleted";

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, SQLiteDBInfo.DB, null, SQLiteDBInfo.VERSION);
    }

    @Override
    public void open() {
        db = dbHandler.getWritableDatabase();
    }

    @Override
    public void close() {
        db.close();
    }

    public void setDB(SQLiteDatabase db) {
        this.db = db;
    }

    protected ContentValues getContentValues(String password, long customerId) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_CUSTOMER_ID, customerId);

        return values;
    }

    public long insert(String password, long customerId) {
        long id = 0;

        db.beginTransaction();

        try {
            id = db.insert(TABLE, null, getContentValues(password, customerId));

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return id;
    }

    public void update(String password, long customerId) {
        if (db.inTransaction()) {
            db.beginTransaction();

            try {
                db.update(TABLE, getContentValues(password, customerId), COLUMN_ID + " = ?", new String[]{ String.valueOf(customerId) });

                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        } else {
            db.update(TABLE, getContentValues(password, customerId), COLUMN_ID + " = ?", new String[]{ String.valueOf(customerId) });
        }
    }

    public void delete(long customerId) {
        String query = "UPDATE " + TABLE
                + " SET "
                + COLUMN_DELETED + " = 1"
                + " WHERE "
                + COLUMN_CUSTOMER_ID + " = " + customerId;

        db.beginTransaction();

        try {
            db.execSQL(query);

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public String select(long customerId) {
        String password = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                + COLUMN_CUSTOMER_ID + " = ? AND "
                + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(customerId), String.valueOf(0) });
        if (cursor.moveToNext()) {
            password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
        }
        cursor.close();

        return password;
    }
}
package com.sasd13.flousy.dao.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLitePasswordDAO {

    public static final String TABLE = "passwords";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CUSTOMER_ID = "customer_id";
    public static final String COLUMN_DELETED = "deleted";

    private SQLiteDatabase db;

    public SQLitePasswordDAO(SQLiteDatabase db) {
        this.db = db;
    }

    protected ContentValues getContentValues(String password, long customerId) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_CUSTOMER_ID, customerId);

        return values;
    }

    public long insert(String password, long customerId) {
        return db.insert(TABLE, null, getContentValues(password, customerId));
    }

    public void update(String password, long customerId) {
        db.update(TABLE, getContentValues(password, customerId), COLUMN_ID + " = ?", new String[]{ String.valueOf(customerId) });
    }

    public void delete(long customerId) {
        String query = "UPDATE " + TABLE
                + " SET "
                    + COLUMN_DELETED + " = 1"
                + " WHERE "
                    + COLUMN_CUSTOMER_ID + " = " + customerId;

        db.execSQL(query);
    }

    public boolean contains(String password, long customerId) {
        boolean contains = false;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_PASSWORD + " = ? AND "
                    + COLUMN_CUSTOMER_ID + " = ? AND "
                    + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ password, String.valueOf(customerId), String.valueOf(0) });
        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
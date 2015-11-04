package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Samir on 05/06/2015.
 */
abstract class SQLiteTableDAO<T> {

    protected SQLiteDatabase db;

    protected SQLiteTableDAO() {}

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    protected abstract ContentValues getContentValues(T t);

    protected abstract T extractCursorValues(Cursor cursor);
}

package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Samir on 05/06/2015.
 */
abstract class SQLiteTableDAO<T> {

    private SQLiteDatabase db;
    private SQLiteDBHandler dbHandler;

    protected SQLiteTableDAO() {}

    protected SQLiteDatabase getDB() { return this.db; }

    public void setDBHandler(SQLiteDBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public void open() { this.db = this.dbHandler.getWritableDatabase(); }

    public void close() { this.db.close(); }

    protected abstract ContentValues getContentValues(T t);

    protected abstract T getCursorValues(Cursor cursor);
}

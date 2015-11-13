package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class SQLiteTableDAO<T> {

    private SQLiteDatabase db;
    private SQLiteDBHandler dbHandler;

    protected SQLiteTableDAO(SQLiteDBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    protected SQLiteDatabase getDB() { return this.db; }

    public void open() { this.db = this.dbHandler.getWritableDatabase(); }

    public void close() { this.db.close(); }

    protected abstract ContentValues getContentValues(T t);

    protected abstract T getCursorValues(Cursor cursor);
}

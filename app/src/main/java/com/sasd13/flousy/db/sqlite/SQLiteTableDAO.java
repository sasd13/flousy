package com.sasd13.flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class SQLiteTableDAO<T> {

    private SQLiteDatabase db;

    protected SQLiteDatabase getDB() { return this.db; }

    public void setDB(SQLiteDatabase db) { this.db = db; }

    protected abstract ContentValues getContentValues(T t);

    protected abstract T getCursorValues(Cursor cursor);
}

package com.sasd13.flousy.db.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.javaex.dao.ISession;

/**
 * Created by Samir on 12/01/2016.
 */
public abstract class SQLiteEntityDAO<T> implements ISession<T> {

    protected SQLiteDatabase db;

    public void setDB(SQLiteDatabase db) {
        this.db = db;
    }

    protected abstract ContentValues getContentValues(T t);

    protected abstract T getCursorValues(Cursor cursor);
}

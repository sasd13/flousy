package com.sasd13.flousy.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Samir on 12/01/2016.
 */
public abstract class AbstractDAO<T> {

    protected SQLiteDatabase db;

    public AbstractDAO(SQLiteDatabase db) {
        this.db = db;
    }

    protected abstract ContentValues getContentValues(T t);

    protected abstract T getCursorValues(Cursor cursor);
}

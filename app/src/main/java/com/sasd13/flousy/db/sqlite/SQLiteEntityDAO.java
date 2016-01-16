package com.sasd13.flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.javaex.db.IEntityDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samir on 12/01/2016.
 */
public abstract class SQLiteEntityDAO<T> implements IEntityDAO<T> {

    protected SQLiteDatabase db;

    public void setDB(SQLiteDatabase db) {
        this.db = db;
    }

    protected abstract ContentValues getContentValues(T t);

    protected abstract T getCursorValues(Cursor cursor);

    protected long executeInsert(String table, T t) {
        long id = db.insert(table, null, getContentValues(t));

        return (id > 0) ? id : 0;
    }

    protected void executeUpdate(String table, T t, String columnId, long id) {
        db.update(table, getContentValues(t), columnId + " = ?", new String[]{String.valueOf(id)});
    }

    protected void executeDelete(String table, String columnId, long id) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_DELETED, 1);

        db.update(table, values, columnId + " = ?", new String[]{String.valueOf(id)});
    }

    protected T executeSelectById(String query, long id) {
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        return getSingleResult(cursor);
    }

    protected List<T> executeSelectAll(String query) {
        Cursor cursor = db.rawQuery(query, null);

        return getMultiResult(cursor);
    }

    protected T getSingleResult(Cursor cursor) {
        List<T> list = getMultiResult(cursor);

        return list.isEmpty() ? null : list.get(0);
    }

    protected List<T> getMultiResult(Cursor cursor) {
        List<T> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                list.add(getCursorValues(cursor));
            }
        }
        cursor.close();

        return list;
    }
}

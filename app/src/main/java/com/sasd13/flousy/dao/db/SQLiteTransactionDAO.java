package com.sasd13.flousy.dao.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Transaction;
import com.sasd13.flousy.dao.IPersistable;
import com.sasd13.flousy.dao.TransactionDAO;
import com.sasd13.flousy.dao.db.util.SQLWhereClauseException;
import com.sasd13.flousy.dao.db.util.SQLWhereClauseParser;

public class SQLiteTransactionDAO extends SQLiteEntityDAO<Transaction> implements TransactionDAO, IPersistable<Transaction> {

    @Override
    protected ContentValues getContentValues(Transaction transaction) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATEREALIZATION, String.valueOf(transaction.getDateRealization()));
        values.put(COLUMN_TITLE, transaction.getTitle());
        values.put(COLUMN_AMOUNT, transaction.getAmount());
        values.put(COLUMN_ACCOUNT_ID, transaction.getAccount().getId());

        return values;
    }

    @Override
    protected Transaction getCursorValues(Cursor cursor) {
        Account account = new Account();
        account.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ACCOUNT_ID)));

        Transaction transaction = new Transaction(account);
        transaction.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        transaction.setDateRealization(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_DATEREALIZATION))));
        transaction.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        transaction.setAmount(cursor.getDouble(cursor.getColumnIndex(COLUMN_AMOUNT)));

        return transaction;
    }

    @Override
    public long insert(Transaction transaction) {
        long id = 0;

        db.beginTransaction();

        try {
            id = db.insert(TABLE, null, getContentValues(transaction));

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return id;
    }

    @Override
    public void update(Transaction transaction) {
        if (db.inTransaction()) {
            db.beginTransaction();

            try {
                db.update(TABLE, getContentValues(transaction), COLUMN_ID + " = ?", new String[]{ String.valueOf(transaction.getId()) });

                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        } else {
            db.update(TABLE, getContentValues(transaction), COLUMN_ID + " = ?", new String[]{ String.valueOf(transaction.getId()) });
        }
    }

    @Override
    public void delete(Transaction transaction) {
        String query = "UPDATE " + TABLE
                + " SET "
                + COLUMN_DELETED + " = 1"
                + " WHERE "
                + COLUMN_ID + " = " + transaction.getId();

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

    @Override
    public Transaction select(long id) {
        Transaction transaction = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                + COLUMN_ID + " = ? AND "
                + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            transaction = getCursorValues(cursor);
        }
        cursor.close();

        return transaction;
    }

    @Override
    public List<Transaction> select(Map<String, String[]> parameters) {
        List<Transaction> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE "
                    + SQLWhereClauseParser.parse(parameters, TransactionDAO.class) + " AND "
                    + COLUMN_DELETED + " = ?";

            Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(0) });
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (SQLWhereClauseException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Transaction> selectAll() {
        List<Transaction> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(0) });
        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public void persist(Transaction transaction) {
        if (select(transaction.getId()) == null) {
            insert(transaction);
        } else {
            update(transaction);
        }
    }
}
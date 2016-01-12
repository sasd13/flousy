package com.sasd13.flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;
import java.util.List;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Transaction;
import com.sasd13.flousy.db.TransactionDAO;

public class SQLiteTransactionDAO extends SQLiteEntityDAO<Transaction> implements TransactionDAO {

    @Override
    protected ContentValues getContentValues(Transaction transaction) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATEREALIZATION, String.valueOf(transaction.getDateRealization()));
        values.put(COLUMN_TITLE, transaction.getTitle());
        values.put(COLUMN_VALUE, transaction.getValue());
        values.put(COLUMN_ACCOUNT_ID, transaction.getAccount().getId());

        return values;
    }

    @Override
    protected Transaction getCursorValues(Cursor cursor) {
        Transaction transaction = new Transaction();

        transaction.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        transaction.setDateRealization(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_DATEREALIZATION))));
        transaction.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        transaction.setValue(cursor.getDouble(cursor.getColumnIndex(COLUMN_VALUE)));

        Account account = new Account();
        account.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ACCOUNT_ID)));
        transaction.setAccount(account);

        return transaction;
    }

    @Override
    public long insert(Transaction transaction) {
        long id = executeInsert(TABLE, transaction);

        transaction.setId(id);

        return id;
    }

    @Override
    public void update(Transaction transaction) {
        executeUpdate(TABLE, transaction, COLUMN_ID, transaction.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(TABLE, COLUMN_ID, id);
    }

    @Override
    public Transaction select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Transaction> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
    }

    @Override
    public List<Transaction> selectByAccount(long accountId) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ACCOUNT_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(accountId)});

        return executeSelectMultiResult(cursor);
    }
}
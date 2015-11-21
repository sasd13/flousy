package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import flousy.bean.Transaction;

public class SQLiteTransactionDAO extends SQLiteTableDAO<Transaction> implements flousy.db.TransactionDAO {

    @Override
    protected ContentValues getContentValues(Transaction transaction) {
        ContentValues values = new ContentValues();

        //values.put(TRANSACTION_ID, transaction.getId()); //autoincrement
        values.put(TRANSACTION_DATEREALIZATION, String.valueOf(transaction.getDateRealization()));
        values.put(TRANSACTION_TITLE, transaction.getTitle());
        values.put(TRANSACTION_VALUE, transaction.getValue());

        return values;
    }

    @Override
    protected Transaction getCursorValues(Cursor cursor) {
        Transaction transaction = new Transaction();

        transaction.setId(cursor.getLong(cursor.getColumnIndex(TRANSACTION_ID)));
        transaction.setDateRealization(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(TRANSACTION_DATEREALIZATION))));
        transaction.setTitle(cursor.getString(cursor.getColumnIndex(TRANSACTION_TITLE)));
        transaction.setValue(cursor.getDouble(cursor.getColumnIndex(TRANSACTION_VALUE)));

        return transaction;
    }

    @Override
    public long insert(Transaction transaction, long accountId) {
        ContentValues values = getContentValues(transaction);
        values.put(ACCOUNTS_ACCOUNT_ID, accountId);

        return getDB().insert(TRANSACTION_TABLE_NAME, null, values);
    }

    @Override
    public void update(Transaction transaction) {
        getDB().update(TRANSACTION_TABLE_NAME, getContentValues(transaction), TRANSACTION_ID + " = ?", new String[]{String.valueOf(transaction.getId())});
    }

    @Override
    public void delete(long id) {
        getDB().delete(TRANSACTION_TABLE_NAME, TRANSACTION_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public Transaction select(long id) {
        Transaction transaction = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TRANSACTION_TABLE_NAME
                        + " where " + TRANSACTION_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            transaction = getCursorValues(cursor);
        }
        cursor.close();

        return transaction;
    }

    @Override
    public List<Transaction> selectByAccount(long accountId) {
        return selectByAccount(accountId, false);
    }

    @Override
    public List<Transaction> selectByAccount(long accountId, boolean ascOrderedByDateRealization) {
        List<Transaction> listTransactions = new ArrayList<>();

        String order = ascOrderedByDateRealization ? "ASC" : "DESC";

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TRANSACTION_TABLE_NAME
                        + " where " + ACCOUNTS_ACCOUNT_ID + " = ?"
                        + " order by " + TRANSACTION_DATEREALIZATION + " " + order, new String[]{String.valueOf(accountId)});

        while (cursor.moveToNext()) {
            listTransactions.add(getCursorValues(cursor));
        }
        cursor.close();

        return listTransactions;
    }
}
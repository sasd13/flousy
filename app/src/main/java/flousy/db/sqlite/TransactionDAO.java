package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import flousy.bean.Transaction;
import flousy.db.TransactionTableAccessor;

public class TransactionDAO extends SQLiteTableDAO<Transaction> implements TransactionTableAccessor {

    public TransactionDAO() { super(); }

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
        values.put(TRANSACTION_DELETED, false);
        values.put(ACCOUNTS_ACCOUNT_ID, accountId);

        return getDB().insert(TRANSACTION_TABLE_NAME, null, values);
    }

    @Override
    public void update(Transaction transaction) {
        getDB().update(TRANSACTION_TABLE_NAME, getContentValues(transaction), TRANSACTION_ID + " = ?", new String[]{String.valueOf(transaction.getId())});
    }

    @Override
    public void delete(long id) {
        Transaction transaction = select(id);

        try {
            ContentValues values = getContentValues(transaction);
            values.put(TRANSACTION_DELETED, true);

            getDB().update(TRANSACTION_TABLE_NAME, values, TRANSACTION_ID + " = ?", new String[]{String.valueOf(id)});
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction select(long id) {
        return select(id, true);
    }

    public Transaction select(long id, boolean excludeDeleted) {
        Transaction transaction = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TRANSACTION_TABLE_NAME
                        + " where " + TRANSACTION_ID + " = ?" + getConditionDeleted(excludeDeleted), new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            transaction = getCursorValues(cursor);
        }
        cursor.close();

        return transaction;
    }

    private String getConditionDeleted(boolean excludeDeleted) {
        return (excludeDeleted) ? " and " + TRANSACTION_DELETED + " = 0" : "";
    }

    @Override
    public List<Transaction> selectByAccount(long accountId) {
        return selectByAccount(accountId, false);
    }

    @Override
    public List<Transaction> selectByAccount(long accountId, boolean ascOrderedByDateRealization) {
        return selectByAccount(accountId, ascOrderedByDateRealization, true);
    }

    public List<Transaction> selectByAccount(long accountId, boolean ascOrderedByDateRealization, boolean excludeDeleted) {
        List<Transaction> listTransactions = new ArrayList<>();

        String order = ascOrderedByDateRealization ? "ASC" : "DESC";

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TRANSACTION_TABLE_NAME
                        + " where " + ACCOUNTS_ACCOUNT_ID + " = ?" + getConditionDeleted(excludeDeleted)
                        + " order by " + TRANSACTION_DATEREALIZATION + " " + order, new String[]{String.valueOf(accountId)});

        while (cursor.moveToNext()) {
            listTransactions.add(getCursorValues(cursor));
        }
        cursor.close();

        return listTransactions;
    }
}
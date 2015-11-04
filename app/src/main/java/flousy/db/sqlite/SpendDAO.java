package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;

import flousy.content.customer.Account;
import flousy.content.spend.ListSpends;
import flousy.content.spend.Spend;
import flousy.db.SpendTableAccessor;
import flousy.util.FlousyCollection;

/**
 * Created by Samir on 02/04/2015.
 */
class SpendDAO extends SQLiteTableDAO<Spend> implements SpendTableAccessor {

    @Override
    protected ContentValues getContentValues(Spend spend) {
        ContentValues values = new ContentValues();

        values.put(SPEND_ID, spend.getId());
        values.put(SPEND_DATE, spend.getDate().toString());
        values.put(SPEND_VALUE, spend.getValue());

        return values;
    }

    @Override
    protected Spend extractCursorValues(Cursor cursor) {
        Spend spend = new Spend();

        spend.setId(cursor.getLong(cursor.getColumnIndex(SPEND_ID)));
        spend.setDate(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(SPEND_DATE))));
        spend.setValue(cursor.getLong(cursor.getColumnIndex(SPEND_VALUE)));

        return spend;
    }

    @Override
    public long insert(Spend spend) {
        return 0;
    }

    @Override
    public long insert(Spend spend, Account account) {
        ContentValues values = getContentValues(spend);
        values.put(ACCOUNTS_ACCOUNT_ID, account.getId());

        return db.insert(SPEND_TABLE_NAME, null, values);
    }

    @Override
    public void update(Spend spend) {
        db.update(SPEND_TABLE_NAME, getContentValues(spend), SPEND_ID + " = ?", new String[]{String.valueOf(spend.getId())});
    }

    @Override
    public void delete(Spend spend) {
        db.delete(SPEND_TABLE_NAME, SPEND_ID + " = ?", new String[]{String.valueOf(spend.getId())});
    }

    @Override
    public Spend select(long id) {
        Spend spend = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + SPEND_TABLE_NAME
                        + " where " + SPEND_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            spend = extractCursorValues(cursor);
        }
        cursor.close();

        return spend;
    }

    @Override
    public FlousyCollection<Spend> selectAll() {
        FlousyCollection<Spend> collection = new ListSpends();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + SPEND_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            collection.add(extractCursorValues(cursor));
        }
        cursor.close();

        return collection;
    }
}

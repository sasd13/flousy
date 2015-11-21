package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;

import flousy.bean.Account;

public class SQLiteAccountDAO extends SQLiteTableDAO<Account> implements flousy.db.AccountDAO {

    @Override
    protected ContentValues getContentValues(Account account) {
        ContentValues values = new ContentValues();

        //values.put(ACCOUNT_ID, account.getId()); //autoincrement
        values.put(ACCOUNT_DATEOPENING, String.valueOf(account.getDateOpening()));
        values.put(ACCOUNT_CLOSED, account.isClosed());

        return values;
    }

    @Override
    protected Account getCursorValues(Cursor cursor) {
        Account account = new Account();

        account.setId(cursor.getLong(cursor.getColumnIndex(ACCOUNT_ID)));
        account.setDateOpening(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(ACCOUNT_DATEOPENING))));
        account.setClosed(cursor.getLong(cursor.getColumnIndex(ACCOUNT_CLOSED)) == 1);

        return account;
    }

    @Override
    public long insert(Account account, long customerId) {
        ContentValues values = getContentValues(account);
        values.put(CUSTOMERS_CUSTOMER_ID, customerId);

        return getDB().insert(ACCOUNT_TABLE_NAME, null, values);
    }

    @Override
    public void update(Account account) {
        getDB().update(ACCOUNT_TABLE_NAME, getContentValues(account), ACCOUNT_ID + " = ?", new String[]{String.valueOf(account.getId())});
    }

    @Override
    public void delete(long id) {
        getDB().delete(ACCOUNT_TABLE_NAME, ACCOUNT_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public Account select(long id) {
        Account account = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + ACCOUNT_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            account = getCursorValues(cursor);
        }
        cursor.close();

        return account;
    }

    @Override
    public Account selectByCustomer(long customerId) {
        Account account = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + CUSTOMERS_CUSTOMER_ID + " = ?", new String[]{String.valueOf(customerId)});

        while (cursor.moveToNext()) {
            account = getCursorValues(cursor);
        }
        cursor.close();

        return account;
    }
}
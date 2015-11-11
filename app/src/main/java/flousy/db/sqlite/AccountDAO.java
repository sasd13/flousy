package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;

import flousy.beans.core.Account;
import flousy.beans.core.User;
import flousy.db.AccountTableAccessor;

/**
 * Created by Samir on 02/04/2015.
 */
public class AccountDAO extends SQLiteTableDAO<Account> implements AccountTableAccessor {

    public AccountDAO(SQLiteDBHandler dbHandler) { super(dbHandler); }

    @Override
    protected ContentValues getContentValues(Account account) {
        ContentValues values = new ContentValues();

        //values.put(ACCOUNT_ID, account.getId()); //autoincrement
        values.put(ACCOUNT_DATEOPENING, String.valueOf(account.getDateOpening()));
        values.put(ACCOUNT_SOLD, account.getSold());

        return values;
    }

    @Override
    protected Account getCursorValues(Cursor cursor) {
        Account account = new Account();

        account.setId(cursor.getLong(cursor.getColumnIndex(ACCOUNT_ID)));
        account.setDateOpening(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(ACCOUNT_DATEOPENING))));
        account.setSold(cursor.getDouble(cursor.getColumnIndex(ACCOUNT_SOLD)));

        return account;
    }

    @Override
    public long insert(Account account) {
        return 0;
    }

    @Override
    public long insert(Account account, User user) {
        ContentValues values = getContentValues(account);
        values.put(USERS_USER_ID, user.getId());

        return getDB().insert(ACCOUNT_TABLE_NAME, null, values);
    }

    @Override
    public void update(Account account) {
        getDB().update(ACCOUNT_TABLE_NAME, getContentValues(account), ACCOUNT_ID + " = ?", new String[]{String.valueOf(account.getId())});
    }

    @Override
    public void delete(Account account) {
        getDB().delete(ACCOUNT_TABLE_NAME, ACCOUNT_ID + " = ?", new String[]{String.valueOf(account.getId())});
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
    public Account selectByUser(long userId) {
        Account account = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + USERS_USER_ID + " = ?", new String[]{String.valueOf(userId)});

        if (cursor.moveToNext()) {
            account = getCursorValues(cursor);
        }
        cursor.close();

        return account;
    }
}

package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;

import flousy.bean.Account;
import flousy.db.AccountTableAccessor;

public class AccountDAO extends SQLiteTableDAO<Account> implements AccountTableAccessor {

    public AccountDAO() { super(); }

    @Override
    protected ContentValues getContentValues(Account account) {
        ContentValues values = new ContentValues();

        //values.put(ACCOUNT_ID, account.getId()); //autoincrement
        values.put(ACCOUNT_DATEOPENING, String.valueOf(account.getDateOpening()));
        values.put(ACCOUNT_USERFIRSTNAME, account.getUserFirstName());
        values.put(ACCOUNT_USERLASTNAME, account.getUserLastName());
        values.put(ACCOUNT_USEREMAIL, account.getUserEmail());
        values.put(ACCOUNT_USERPASSWORD, account.getUserPassword());

        return values;
    }

    @Override
    protected Account getCursorValues(Cursor cursor) {
        Account account = new Account();

        account.setId(cursor.getLong(cursor.getColumnIndex(ACCOUNT_ID)));
        account.setDateOpening(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(ACCOUNT_DATEOPENING))));
        account.setUserFirstName(cursor.getString(cursor.getColumnIndex(ACCOUNT_USERFIRSTNAME)));
        account.setUserLastName(cursor.getString(cursor.getColumnIndex(ACCOUNT_USERLASTNAME)));
        account.setUserEmail(cursor.getString(cursor.getColumnIndex(ACCOUNT_USEREMAIL)));
        account.setUserPassword(cursor.getString(cursor.getColumnIndex(ACCOUNT_USERPASSWORD)));

        return account;
    }

    @Override
    public void insert(Account account) {
        ContentValues values = getContentValues(account);
        values.put(ACCOUNT_DELETED, false);

        getDB().insert(ACCOUNT_TABLE_NAME, null, values);
    }

    @Override
    public void update(Account account) {
        getDB().update(ACCOUNT_TABLE_NAME, getContentValues(account), ACCOUNT_USEREMAIL + " = ?", new String[]{String.valueOf(account.getUserEmail())});
    }

    @Override
    public void delete(long id) {
        Account account = select(id);

        try {
            ContentValues values = getContentValues(account);
            values.put(ACCOUNT_DELETED, true);

            getDB().update(ACCOUNT_TABLE_NAME, values, ACCOUNT_ID + " = ?", new String[]{String.valueOf(id)});
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account select(long id) {
        return select(id, true);
    }

    public Account select(long id, boolean excludeDeleted) {
        Account account = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + ACCOUNT_ID + " = ?" + getConditionDeleted(excludeDeleted), new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            account = getCursorValues(cursor);
        }
        cursor.close();

        return account;
    }

    private String getConditionDeleted(boolean excludeDeleted) {
        return (excludeDeleted) ? " and " + ACCOUNT_DELETED + " = 0" : "";
    }

    @Override
    public Account selectByUserEmail(String userEmail) {
        return selectByUserEmail(userEmail, true);
    }

    public Account selectByUserEmail(String userEmail, boolean excludeDeleted) {
        Account account = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + ACCOUNT_USEREMAIL + " = ?" + getConditionDeleted(excludeDeleted), new String[]{String.valueOf(userEmail)});

        if (cursor.moveToNext()) {
            account = getCursorValues(cursor);
        }
        cursor.close();

        return account;
    }

    @Override
    public boolean containsByUserEmail(String userEmail) {
        return containsByUserEmail(userEmail, true);
    }

    public boolean containsByUserEmail(String userEmail, boolean excludeDeleted) {
        boolean contains = false;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + ACCOUNT_USEREMAIL + " = ?" + getConditionDeleted(excludeDeleted), new String[]{String.valueOf(userEmail)});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
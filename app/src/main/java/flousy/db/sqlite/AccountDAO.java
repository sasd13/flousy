package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.content.customer.Account;
import flousy.content.customer.Customer;
import flousy.content.customer.ListAccounts;
import flousy.db.AccountTableAccessor;
import flousy.util.FlousyCollection;

/**
 * Created by Samir on 02/04/2015.
 */
class AccountDAO extends SQLiteTableDAO<Account> implements AccountTableAccessor {

    @Override
    protected ContentValues getContentValues(Account account) {
        ContentValues values = new ContentValues();

        values.put(ACCOUNT_ID, account.getId());

        return values;
    }

    @Override
    protected Account extractCursorValues(Cursor cursor) {
        Account account = new Account();

        account.setId(cursor.getLong(cursor.getColumnIndex(ACCOUNT_ID)));

        return account;
    }

    @Override
    public long insert(Account account) {
        return 0;
    }

    @Override
    public long insert(Account account, Customer customer) {
        ContentValues values = getContentValues(account);
        values.put(CUSTOMERS_CUSTOMER_ID, customer.getId());
        
        return db.insert(ACCOUNT_TABLE_NAME, null, values);
    }

    @Override
    public void update(Account account) {
        db.update(ACCOUNT_TABLE_NAME, getContentValues(account), ACCOUNT_ID + " = ?", new String[]{String.valueOf(account.getId())});
    }

    @Override
    public void delete(Account account) {
        db.delete(ACCOUNT_TABLE_NAME, ACCOUNT_ID + " = ?", new String[]{String.valueOf(account.getId())});
    }

    @Override
    public Account select(long id) {
        Account account = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + ACCOUNT_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            account = extractCursorValues(cursor);
        }
        cursor.close();

        return account;
    }

    @Override
    public FlousyCollection<Account> selectAll() {
        FlousyCollection<Account> collection = new ListAccounts();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            collection.add(extractCursorValues(cursor));
        }
        cursor.close();

        return collection;
    }
}

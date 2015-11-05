package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;

import flousy.bean.trading.ITradingAccount;
import flousy.bean.customer.Customer;
import flousy.bean.trading.ListTradingAccounts;
import flousy.bean.trading.TradingAccountFactory;
import flousy.bean.trading.TradingException;
import flousy.db.AccountTableAccessor;

/**
 * Created by Samir on 02/04/2015.
 */
class AccountDAO extends SQLiteTableDAO<ITradingAccount> implements AccountTableAccessor {

    @Override
    protected ContentValues getContentValues(ITradingAccount tradingAccount) {
        ContentValues values = new ContentValues();

        values.put(ACCOUNT_ID, tradingAccount.getId());
        values.put(ACCOUNT_DATEOPENING, tradingAccount.getDateOpening().toString());
        values.put(ACCOUNT_TYPE, tradingAccount.getTradingAccountType());
        values.put(ACCOUNT_SOLD, tradingAccount.getSold());

        return values;
    }

    @Override
    protected ITradingAccount extractCursorValues(Cursor cursor) {
        ITradingAccount iTradingAccount = null;

        String accountType = cursor.getString(cursor.getColumnIndex(ACCOUNT_TYPE));

        try {
            iTradingAccount = TradingAccountFactory.create(accountType);
            iTradingAccount.setId(cursor.getLong(cursor.getColumnIndex(ACCOUNT_ID)));
            iTradingAccount.setDateOpening(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(ACCOUNT_DATEOPENING))));
        } catch (TradingException e) {
            e.printStackTrace();
        }

        return iTradingAccount;
    }

    @Override
    public long insert(ITradingAccount tradingAccount) {
        return 0;
    }

    @Override
    public long insert(ITradingAccount tradingAccount, Customer customer) {
        ContentValues values = getContentValues(tradingAccount);
        values.put(CUSTOMERS_CUSTOMER_ID, customer.getId());
        
        return db.insert(ACCOUNT_TABLE_NAME, null, values);
    }

    @Override
    public void update(ITradingAccount tradingAccount) {
        db.update(ACCOUNT_TABLE_NAME, getContentValues(tradingAccount), ACCOUNT_ID + " = ?", new String[]{String.valueOf(tradingAccount.getId())});
    }

    @Override
    public void delete(ITradingAccount tradingAccount) {
        db.delete(ACCOUNT_TABLE_NAME, ACCOUNT_ID + " = ?", new String[]{String.valueOf(tradingAccount.getId())});
    }

    @Override
    public ITradingAccount select(long id) {
        ITradingAccount tradingAccount = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + ACCOUNT_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            tradingAccount = extractCursorValues(cursor);
        }
        cursor.close();

        return tradingAccount;
    }

    @Override
    public ListTradingAccounts selectAll() {
        ListTradingAccounts listTradingAccounts = new ListTradingAccounts();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            listTradingAccounts.add(extractCursorValues(cursor));
        }
        cursor.close();

        return listTradingAccounts;
    }

    @Override
    public ITradingAccount selectAccountByCustomer(long customerId) {
        ITradingAccount tradingAccount = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + CUSTOMERS_CUSTOMER_ID + " = ?", new String[]{String.valueOf(customerId)});

        if (cursor.moveToNext()) {
            tradingAccount = extractCursorValues(cursor);
        }
        cursor.close();

        return tradingAccount;
    }
}

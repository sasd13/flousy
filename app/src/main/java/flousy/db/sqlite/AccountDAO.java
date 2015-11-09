package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;

import flousy.bean.trading.ITradingAccount;
import flousy.bean.user.User;
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
        values.put(ACCOUNT_DATEOPENING, String.valueOf(tradingAccount.getDateOpening()));
        values.put(ACCOUNT_TYPE, tradingAccount.getTradingAccountType());
        values.put(ACCOUNT_SOLD, tradingAccount.getSold());

        return values;
    }

    @Override
    protected ITradingAccount getCursorValues(Cursor cursor) {
        ITradingAccount tradingAccount = null;

        String accountType = cursor.getString(cursor.getColumnIndex(ACCOUNT_TYPE));

        try {
            tradingAccount = TradingAccountFactory.create(accountType);

            tradingAccount.setId(cursor.getLong(cursor.getColumnIndex(ACCOUNT_ID)));
            tradingAccount.setDateOpening(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(ACCOUNT_DATEOPENING))));
        } catch (TradingException e) {
            e.printStackTrace();
        }

        return tradingAccount;
    }

    @Override
    public long insert(ITradingAccount tradingAccount) {
        return 0;
    }

    @Override
    public long insert(ITradingAccount tradingAccount, User user) {
        ContentValues values = getContentValues(tradingAccount);
        values.put(USERS_USER_ID, user.getId());

        return getDB().insert(ACCOUNT_TABLE_NAME, null, values);
    }

    @Override
    public void update(ITradingAccount tradingAccount) {
        getDB().update(ACCOUNT_TABLE_NAME, getContentValues(tradingAccount), ACCOUNT_ID + " = ?", new String[]{String.valueOf(tradingAccount.getId())});
    }

    @Override
    public void delete(ITradingAccount tradingAccount) {
        getDB().delete(ACCOUNT_TABLE_NAME, ACCOUNT_ID + " = ?", new String[]{String.valueOf(tradingAccount.getId())});
    }

    @Override
    public ITradingAccount select(long id) {
        ITradingAccount tradingAccount = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + ACCOUNT_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            tradingAccount = getCursorValues(cursor);
        }
        cursor.close();

        return tradingAccount;
    }

    @Override
    public ITradingAccount selectByUser(long userId) {
        ITradingAccount tradingAccount = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + ACCOUNT_TABLE_NAME
                        + " where " + USERS_USER_ID + " = ?", new String[]{String.valueOf(userId)});

        if (cursor.moveToNext()) {
            tradingAccount = getCursorValues(cursor);
        }
        cursor.close();

        return tradingAccount;
    }
}

package flousy.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.content.spend.SpendsAccount;

/**
 * Created by Samir on 02/04/2015.
 */
class SpendsAccountDAO extends AbstractTableDAO {

    public static final String SPENDSACCOUNT_TABLE_NAME = "spends_accounts";

    public static final String SPENDSACCOUNT_ID = "account_id";
    public static final String SPENDSACCOUNT_CLIENT_ID = "account_client_id";

    public long insert(SpendsAccount spendsAccount, String clientId) {
        ContentValues values = getContentValues(spendsAccount);
        values.put(SPENDSACCOUNT_CLIENT_ID, clientId);

        return db.insert(SPENDSACCOUNT_TABLE_NAME, null, values);
    }

    private ContentValues getContentValues(SpendsAccount spendsAccount) {
        ContentValues values = new ContentValues();

        values.put(SPENDSACCOUNT_ID, spendsAccount.getId());

        return values;
    }

    public long update(SpendsAccount spendsAccount) {
        return db.update(SPENDSACCOUNT_TABLE_NAME, getContentValues(spendsAccount), SPENDSACCOUNT_ID + " = ?",
                new String[]{spendsAccount.getId()});
    }

    public long delete(SpendsAccount spendsAccount) {
        return db.delete(SPENDSACCOUNT_TABLE_NAME, SPENDSACCOUNT_ID + " = ?", new String[]{spendsAccount.getId()});
    }

    public SpendsAccount select(String spendsAccountIdOrClientId) {
        SpendsAccount spendsAccount = null;

        Cursor cursor = db.rawQuery(
                "select " + SPENDSACCOUNT_ID
                        + " from " + SPENDSACCOUNT_TABLE_NAME
                        + " where " + SPENDSACCOUNT_ID + " = ? or " + SPENDSACCOUNT_CLIENT_ID + " = ?",
                new String[]{spendsAccountIdOrClientId, spendsAccountIdOrClientId});

        if (cursor.moveToNext()) {
            spendsAccount = new SpendsAccount();
            spendsAccount.setId(cursor.getString(0));
        }
        cursor.close();

        try {
            spendsAccount.setListSpends(SQLiteDAO.getInstance().selectSpendsOfSpendsAccount(spendsAccount.getId()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return spendsAccount;
    }

    public boolean contains(String spendsAccountIdOrClientId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + SPENDSACCOUNT_ID
                        + " from " + SPENDSACCOUNT_TABLE_NAME
                        + " where " + SPENDSACCOUNT_ID + " = ? or " + SPENDSACCOUNT_CLIENT_ID + " = ?",
                new String[]{spendsAccountIdOrClientId, spendsAccountIdOrClientId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}

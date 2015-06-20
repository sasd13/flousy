package flousy.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.content.finance.PaymentsAccount;

/**
 * Created by Samir on 02/04/2015.
 */
class PaymentsAccountDAO extends AbstractTableDAO {

    public static final String PAYMENTSACCOUNT_TABLE_NAME = "payments_accounts";

    public static final String PAYMENTSACCOUNT_ID = "account_id";
    public static final String PAYMENTSACCOUNT_CLIENT_ID = "account_client_id";

    public long insert(PaymentsAccount paymentsAccount, String clientId) {
        ContentValues values = getContentValues(paymentsAccount);
        values.put(PAYMENTSACCOUNT_CLIENT_ID, clientId);

        return db.insert(PAYMENTSACCOUNT_TABLE_NAME, null, values);
    }

    private ContentValues getContentValues(PaymentsAccount paymentsAccount) {
        ContentValues values = new ContentValues();

        values.put(PAYMENTSACCOUNT_ID, paymentsAccount.getId());

        return values;
    }

    public long update(PaymentsAccount paymentsAccount) {
        return db.update(PAYMENTSACCOUNT_TABLE_NAME, getContentValues(paymentsAccount), PAYMENTSACCOUNT_ID + " = ?",
                new String[]{paymentsAccount.getId()});
    }

    public long delete(PaymentsAccount paymentsAccount) {
        return db.delete(PAYMENTSACCOUNT_TABLE_NAME, PAYMENTSACCOUNT_ID + " = ?",
                new String[]{paymentsAccount.getId()});
    }

    public PaymentsAccount select(String paymentsAccountIdOrClientId) {
        PaymentsAccount paymentsAccount = null;

        Cursor cursor = db.rawQuery(
                "select " + PAYMENTSACCOUNT_ID
                        + " from " + PAYMENTSACCOUNT_TABLE_NAME
                        + " where " + PAYMENTSACCOUNT_ID + " = ? or " + PAYMENTSACCOUNT_CLIENT_ID + " = ?",
                new String[]{paymentsAccountIdOrClientId, paymentsAccountIdOrClientId});

        if (cursor.moveToNext()) {
            paymentsAccount = new PaymentsAccount();
            paymentsAccount.setId(cursor.getString(0));
        }
        cursor.close();

        try {
            paymentsAccount.setListPayments(SQLiteDAO.getInstance().selectPaymentsOfPaymentsAccount(paymentsAccount.getId()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return paymentsAccount;
    }

    public boolean contains(String paymentsAccountIdOrClientId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + PAYMENTSACCOUNT_ID
                        + " from " + PAYMENTSACCOUNT_TABLE_NAME
                        + " where " + PAYMENTSACCOUNT_ID + " = ? or " + PAYMENTSACCOUNT_CLIENT_ID + " = ?",
                new String[]{paymentsAccountIdOrClientId, paymentsAccountIdOrClientId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}

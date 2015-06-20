package flousy.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

import flousy.content.finance.ListPayments;
import flousy.content.finance.Payment;

/**
 * Created by Samir on 02/04/2015.
 */
class PaymentDAO extends AbstractTableDAO {

    public static final String PAYMENT_TABLE_NAME = "payments";

    public static final String PAYMENT_ID = "payment_id";
    public static final String PAYMENT_VALUE = "payment_value";
    public static final String PAYMENT_DATE = "payment_date";
    public static final String PAYMENT_ACCOUNT_ID = "payment_account_id";

    public long insert(Payment payment, String paymentsAccountId) {
        ContentValues values = getContentValues(payment);
        values.put(PAYMENT_ACCOUNT_ID, paymentsAccountId);

        return db.insert(PAYMENT_TABLE_NAME, null, values);
    }

    private ContentValues getContentValues(Payment payment) {
        ContentValues values = new ContentValues();

        values.put(PAYMENT_ID, payment.getId());
        values.put(PAYMENT_VALUE, payment.getValue());
        values.put(PAYMENT_DATE, payment.getDate().getTime());

        return values;
    }

    public long update(Payment payment) {
        return db.update(PAYMENT_TABLE_NAME, getContentValues(payment), PAYMENT_ID + " = ?", new String[]{payment.getId()});
    }

    public long delete(Payment payment) {
        return db.delete(PAYMENT_TABLE_NAME, PAYMENT_ID + " = ?", new String[]{payment.getId()});
    }

    public Payment select(String paymentId) {
        Payment payment = null;

        Cursor cursor = db.rawQuery(
                "select " + PAYMENT_VALUE + ", " + PAYMENT_DATE
                        + " from " + PAYMENT_TABLE_NAME
                        + " where " + PAYMENT_ID + " = ?", new String[]{paymentId});

        if (cursor.moveToNext()) {
            payment = new Payment();
            payment.setId(paymentId);
            payment.setValue(cursor.getLong(0));
            payment.setDate(new Date(cursor.getLong(1)));
        }
        cursor.close();

        return payment;
    }

    public ListPayments selectAllOfPaymentsAccount(String paymentsAccountId) {
        ListPayments listPayments = new ListPayments();

        Cursor cursor = db.rawQuery(
                "select " + PAYMENT_ID + ", " + PAYMENT_VALUE + ", " + PAYMENT_DATE
                        + " from " + PAYMENT_TABLE_NAME
                        + " where " + PAYMENT_ACCOUNT_ID + " = ?", new String[]{paymentsAccountId});

        Payment payment;

        while (cursor.moveToNext()) {
            payment = new Payment();
            payment.setId(cursor.getString(0));
            payment.setValue(cursor.getLong(1));
            payment.setDate(new Date(cursor.getLong(2)));
        }
        cursor.close();

        return listPayments;
    }

    public boolean contains(String paymentId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + PAYMENT_ID
                        + " from " + PAYMENT_TABLE_NAME
                        + " where " + PAYMENT_ID + " = ?", new String[]{paymentId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}

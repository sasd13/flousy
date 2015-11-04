package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;

import flousy.content.customer.Account;
import flousy.content.finance.ListPayments;
import flousy.content.finance.Payment;
import flousy.db.PaymentTableAccessor;
import flousy.util.FlousyCollection;

/**
 * Created by Samir on 02/04/2015.
 */
class PaymentDAO extends SQLiteTableDAO<Payment> implements PaymentTableAccessor {

    @Override
    protected ContentValues getContentValues(Payment payment) {
        ContentValues values = new ContentValues();

        values.put(PAYMENT_ID, payment.getId());
        values.put(PAYMENT_DATE, payment.getDate().toString());
        values.put(PAYMENT_VALUE, payment.getValue());

        return values;
    }

    @Override
    protected Payment extractCursorValues(Cursor cursor) {
        Payment payment = new Payment();

        payment.setId(cursor.getLong(cursor.getColumnIndex(PAYMENT_ID)));
        payment.setDate(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(PAYMENT_DATE))));
        payment.setValue(cursor.getLong(cursor.getColumnIndex(PAYMENT_VALUE)));

        return payment;
    }

    @Override
    public long insert(Payment payment) {
        return 0;
    }

    @Override
    public long insert(Payment payment, Account account) {
        ContentValues values = getContentValues(payment);
        values.put(ACCOUNTS_ACCOUNT_ID, account.getId());

        return db.insert(PAYMENT_TABLE_NAME, null, values);
    }

    @Override
    public void update(Payment payment) {
        db.update(PAYMENT_TABLE_NAME, getContentValues(payment), PAYMENT_ID + " = ?", new String[]{String.valueOf(payment.getId())});
    }

    @Override
    public void delete(Payment payment) {
        db.delete(PAYMENT_TABLE_NAME, PAYMENT_ID + " = ?", new String[]{String.valueOf(payment.getId())});
    }

    @Override
    public Payment select(long id) {
        Payment payment = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + PAYMENT_TABLE_NAME
                        + " where " + PAYMENT_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            payment = extractCursorValues(cursor);
        }
        cursor.close();

        return payment;
    }

    @Override
    public FlousyCollection<Payment> selectAll() {
        FlousyCollection<Payment> collection = new ListPayments();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + PAYMENT_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            collection.add(extractCursorValues(cursor));
        }
        cursor.close();

        return collection;
    }
}

package flousy.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.content.Phone;

/**
 * Created by Samir on 02/04/2015.
 */
class PhoneDAO extends AbstractTableDAO {

    public static final String PHONE_TABLE_NAME = "phones";

    public static final String PHONE_ID = "phone_id";
    public static final String PHONE_INDEX = "phone_index";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String PHONE_CLIENT_ID = "phone_client_id";

    public void insert(Phone phone, String clientId) {
        db.insert(PHONE_TABLE_NAME, null, getContentValues(phone, clientId));
    }

    private ContentValues getContentValues(Phone phone, String clientId) {
        ContentValues values = new ContentValues();

        values.put(PHONE_INDEX, phone.getIndex());
        values.put(PHONE_NUMBER, phone.getNumber());
        values.put(PHONE_CLIENT_ID, clientId);

        return values;
    }

    public void update(Phone phone, String clientId) {
        db.update(PHONE_TABLE_NAME, getContentValues(phone, clientId), PHONE_CLIENT_ID + " = ?", new String[]{clientId});
    }

    public long delete(String clientId) {
        return db.delete(PHONE_TABLE_NAME, PHONE_CLIENT_ID + " = ?", new String[]{clientId});
    }

    public Phone select(String clientId) {
        Phone phone = null;

        Cursor cursor = db.rawQuery(
                "select " + PHONE_INDEX + ", " + PHONE_NUMBER
                        + " from " + PHONE_TABLE_NAME
                        + " where " + PHONE_CLIENT_ID + " = ?", new String[]{clientId});

        if (cursor.moveToNext()) {
            phone = new Phone();
            phone.setIndex(cursor.getLong(0));
            phone.setNumber(cursor.getString(1));
        }
        cursor.close();

        return phone;
    }

    public boolean contains(String clientId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + PHONE_ID
                        + " from " + PHONE_TABLE_NAME
                        + " where " + PHONE_CLIENT_ID + " = ?", new String[]{clientId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}

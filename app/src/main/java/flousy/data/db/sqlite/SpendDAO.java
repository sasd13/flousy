package flousy.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.content.Client;

/**
 * Created by Samir on 02/04/2015.
 */
class SpendDAO extends AbstractTableDAO {

    public static final String CLIENT_TABLE_NAME = "clients";

    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_FIRSTNAME = "client_first_name";
    public static final String CLIENT_LASTNAME = "client_last_name";
    public static final String CLIENT_EMAIL = "client_email";

    public long insert(Client client) {
        return db.insert(CLIENT_TABLE_NAME, null, getContentValues(client));
    }

    private ContentValues getContentValues(Client client) {
        ContentValues values = new ContentValues();

        values.put(CLIENT_ID, client.getId().toString());
        values.put(CLIENT_FIRSTNAME, client.getFirstName());
        values.put(CLIENT_LASTNAME, client.getLastName());
        values.put(CLIENT_EMAIL, client.getEmail());

        return values;
    }

    public long update(Client client) {
        return db.update(CLIENT_TABLE_NAME, getContentValues(client), CLIENT_ID + " = ?", new String[]{client.getId()});
    }

    public Client select(String clientIdOrEmail) {
        Client client = null;

        Cursor cursor = db.rawQuery(
                "select " + CLIENT_ID + ", " + CLIENT_FIRSTNAME + ", " + CLIENT_LASTNAME + ", " + CLIENT_EMAIL
                        + " from " + CLIENT_TABLE_NAME
                        + " where " + CLIENT_ID + " = ? or " + CLIENT_EMAIL + " = ?", new String[]{clientIdOrEmail, clientIdOrEmail});

        if (cursor.moveToNext()) {
            client = new Client();
            client.setId(cursor.getString(0));
            client.setFirstName(cursor.getString(1));
            client.setLastName(cursor.getString(2));
            client.setEmail(cursor.getString(3));
        }
        cursor.close();

        return client;
    }

    public boolean contains(String clientIdOrEmail) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + CLIENT_ID
                        + " from " + CLIENT_TABLE_NAME
                        + " where " + CLIENT_ID + " = ? or " + CLIENT_EMAIL + " = ?", new String[]{clientIdOrEmail, clientIdOrEmail});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}

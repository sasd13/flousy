package flousy.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.content.Client;

/**
 * Created by Samir on 02/04/2015.
 */
class ClientDAO extends AbstractTableDAO {

    public static final String CLIENT_TABLE_NAME = "clients";

    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_FIRSTNAME = "client_first_name";
    public static final String CLIENT_LASTNAME = "client_last_name";
    public static final String CLIENT_EMAIL = "client_email";
    public static final String CLIENT_PASSWORD = "client_password";

    public long insert(Client client) {
        return db.insert(CLIENT_TABLE_NAME, null, getContentValues(client));
    }

    private ContentValues getContentValues(Client client) {
        ContentValues values = new ContentValues();

        values.put(CLIENT_ID, client.getId());
        values.put(CLIENT_FIRSTNAME, client.getFirstName());
        values.put(CLIENT_LASTNAME, client.getLastName());
        values.put(CLIENT_EMAIL, client.getEmail());
        values.put(CLIENT_PASSWORD, client.getPassword());

        return values;
    }

    public long update(Client client) {
        return db.update(CLIENT_TABLE_NAME, getContentValues(client), CLIENT_ID + " = ?", new String[]{client.getId()});
    }

    public long delete(Client client) {
        return db.delete(CLIENT_TABLE_NAME, CLIENT_ID + " = ?", new String[]{client.getId()});
    }

    public Client select(String clientIdOrEmail) {
        Client client = null;

        Cursor cursor = db.rawQuery(
                "select " + CLIENT_ID + ", " + CLIENT_FIRSTNAME + ", " + CLIENT_LASTNAME + ", " + CLIENT_EMAIL + ", " + CLIENT_PASSWORD
                        + " from " + CLIENT_TABLE_NAME
                        + " where " + CLIENT_ID + " = ? or " + CLIENT_EMAIL + " = ?", new String[]{clientIdOrEmail, clientIdOrEmail});

        if (cursor.moveToNext()) {
            client = new Client();
            client.setId(cursor.getString(0));
            client.setFirstName(cursor.getString(1));
            client.setLastName(cursor.getString(2));
            client.setEmail(cursor.getString(3));
            client.setPassword(cursor.getString(4));
        }
        cursor.close();

        try {
            String clientId = client.getId();
            SQLiteDAO dao = SQLiteDAO.getInstance();

            client.setPhone(dao.selectPhone(clientId));
            client.setListIncomes(dao.selectIncomesOfClient(clientId));
            client.setPaymentsAccount(dao.selectPaymentsAccount(clientId));
            client.setSpendsAccount(dao.selectSpendsAccount(clientId));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

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

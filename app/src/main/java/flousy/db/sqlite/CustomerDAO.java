package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.content.Phone;
import flousy.content.customer.Customer;
import flousy.content.customer.ListCustomers;
import flousy.db.CustomerTableAccessor;
import flousy.util.FlousyCollection;

/**
 * Created by Samir on 02/04/2015.
 */
class CustomerDAO extends SQLiteTableDAO<Customer> implements CustomerTableAccessor {

    @Override
    protected ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();

        values.put(CUSTOMER_ID, customer.getId());
        values.put(CUSTOMER_FIRSTNAME, customer.getFirstName());
        values.put(CUSTOMER_LASTNAME, customer.getLastName());
        values.put(CUSTOMER_EMAIL, customer.getEmail());
        values.put(CUSTOMER_PASSWORD, customer.getPassword());
        values.put(CUSTOMER_PHONE, customer.getPhone().toString());

        return values;
    }

    @Override
    protected Customer extractCursorValues(Cursor cursor) {
        Customer customer = new Customer();

        customer.setId(cursor.getLong(cursor.getColumnIndex(CUSTOMER_ID)));
        customer.setFirstName(cursor.getString(cursor.getColumnIndex(CUSTOMER_FIRSTNAME)));
        customer.setLastName(cursor.getString(cursor.getColumnIndex(CUSTOMER_LASTNAME)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(CUSTOMER_EMAIL)));
        customer.setPassword(cursor.getString(cursor.getColumnIndex(CUSTOMER_PASSWORD)));
        customer.setPhone(Phone.parse(cursor.getString(cursor.getColumnIndex(CUSTOMER_PHONE))));

        return customer;
    }

    @Override
    public long insert(Customer customer) {
        return db.insert(CUSTOMER_TABLE_NAME, null, getContentValues(customer));
    }

    @Override
    public void update(Customer customer) {
        db.update(CUSTOMER_TABLE_NAME, getContentValues(customer), CUSTOMER_ID + " = ?", new String[]{String.valueOf(customer.getId())});
    }

    @Override
    public void delete(Customer customer) {
        db.delete(CUSTOMER_TABLE_NAME, CUSTOMER_ID + " = ?", new String[]{String.valueOf(customer.getId())});
    }

    @Override
    public Customer select(long id) {
        Customer customer = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + CUSTOMER_TABLE_NAME
                        + " where " + CUSTOMER_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            customer = extractCursorValues(cursor);
        }
        cursor.close();

        return customer;
    }

    @Override
    public Customer selectByEmail(String email) {
        Customer customer = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + CUSTOMER_TABLE_NAME
                        + " where " + CUSTOMER_EMAIL + " = ?", new String[]{email});

        if (cursor.moveToNext()) {
            customer = extractCursorValues(cursor);
        }
        cursor.close();

        return customer;
    }

    @Override
    public FlousyCollection<Customer> selectAll() {
        FlousyCollection<Customer> collection = new ListCustomers();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + CUSTOMER_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            collection.add(extractCursorValues(cursor));
        }
        cursor.close();

        return collection;
    }
}

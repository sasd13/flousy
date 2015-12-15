package com.sasd13.flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.flousy.bean.Customer;

public class SQLiteCustomerDAO extends SQLiteTableDAO<Customer> implements com.sasd13.flousy.db.CustomerDAO {

    @Override
    protected ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();

        //values.put(CUSTOMER_ID, customer.getId()); //autoincrement
        values.put(CUSTOMER_FIRSTNAME, customer.getFirstName());
        values.put(CUSTOMER_LASTNAME, customer.getLastName());
        values.put(CUSTOMER_EMAIL, customer.getEmail());
        values.put(CUSTOMER_PASSWORD, customer.getPassword());

        return values;
    }

    @Override
    protected Customer getCursorValues(Cursor cursor) {
        Customer customer = new Customer();

        customer.setId(cursor.getLong(cursor.getColumnIndex(CUSTOMER_ID)));
        customer.setFirstName(cursor.getString(cursor.getColumnIndex(CUSTOMER_FIRSTNAME)));
        customer.setLastName(cursor.getString(cursor.getColumnIndex(CUSTOMER_LASTNAME)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(CUSTOMER_EMAIL)));
        customer.setPassword(cursor.getString(cursor.getColumnIndex(CUSTOMER_PASSWORD)));

        return customer;
    }

    @Override
    public long insert(Customer customer) {
        return getDB().insert(CUSTOMER_TABLE_NAME, null, getContentValues(customer));
    }

    @Override
    public void update(Customer customer) {
        getDB().update(CUSTOMER_TABLE_NAME, getContentValues(customer), CUSTOMER_ID + " = ?", new String[]{String.valueOf(customer.getId())});
    }

    @Override
    public void delete(long id) {
        getDB().delete(CUSTOMER_TABLE_NAME, CUSTOMER_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public Customer select(long id) {
        Customer customer = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + CUSTOMER_TABLE_NAME
                        + " where " + CUSTOMER_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            customer = getCursorValues(cursor);
        }
        cursor.close();

        return customer;
    }

    @Override
    public Customer selectByEmail(String email) {
        Customer customer = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + CUSTOMER_TABLE_NAME
                        + " where " + CUSTOMER_EMAIL + " = ?", new String[]{String.valueOf(email)});

        if (cursor.moveToNext()) {
            customer = getCursorValues(cursor);
        }
        cursor.close();

        return customer;
    }
}
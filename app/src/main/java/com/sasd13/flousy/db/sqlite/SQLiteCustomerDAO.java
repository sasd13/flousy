package com.sasd13.flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.db.CustomerDAO;

import java.util.List;

public class SQLiteCustomerDAO extends SQLiteEntityDAO<Customer> implements CustomerDAO {

    @Override
    protected ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_FIRSTNAME, customer.getFirstName());
        values.put(COLUMN_LASTNAME, customer.getLastName());
        values.put(COLUMN_EMAIL, customer.getEmail());
        values.put(COLUMN_PASSWORD, customer.getPassword());

        return values;
    }

    @Override
    protected Customer getCursorValues(Cursor cursor) {
        Customer customer = new Customer();

        customer.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        customer.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)));
        customer.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
        customer.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));

        return customer;
    }

    @Override
    public long insert(Customer customer) {
        long id = executeInsert(TABLE, customer);

        customer.setId(id);

        return id;
    }

    @Override
    public void update(Customer customer) {
        executeUpdate(TABLE, customer, COLUMN_ID, customer.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(TABLE, COLUMN_ID, id);
    }

    @Override
    public Customer select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Customer> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
    }

    @Override
    public Customer selectByEmail(String email) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                + COLUMN_EMAIL + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{email});

        return getSingleResult(cursor);
    }
}
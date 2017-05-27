package com.sasd13.flousy.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.dao.ICustomerDAO;

public class CustomerDAO extends AbstractDAO<Customer> implements ICustomerDAO {

    public CustomerDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_FIRSTNAME, customer.getFirstName());
        values.put(COLUMN_LASTNAME, customer.getLastName());
        values.put(COLUMN_EMAIL, customer.getEmail());

        return values;
    }

    @Override
    protected Customer getCursorValues(Cursor cursor) {
        Customer customer = new Customer();

        customer.setIntermediary(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));
        customer.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)));
        customer.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));

        return customer;
    }

    @Override
    public long create(Customer customer) {
        return db.insert(TABLE, null, getContentValues(customer));
    }

    @Override
    public void update(Customer customer) {
        db.update(TABLE, getContentValues(customer), COLUMN_CODE + " = ?", new String[]{String.valueOf(customer.getIntermediary())});
    }

    @Override
    public Customer read(String intermediary) {
        Customer customer = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_CODE + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{String.valueOf(intermediary)});
        if (cursor.moveToNext()) {
            customer = getCursorValues(cursor);
        }
        cursor.close();

        return customer;
    }
}
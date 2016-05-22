package com.sasd13.flousy.dao.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.dao.CustomerDAO;
import com.sasd13.flousy.dao.IPersistable;
import com.sasd13.flousy.dao.db.util.SQLWhereClauseException;
import com.sasd13.flousy.dao.db.util.SQLWhereClauseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteCustomerDAO extends SQLiteEntityDAO<Customer> implements CustomerDAO, IPersistable<Customer> {

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

        customer.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        customer.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)));
        customer.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));

        return customer;
    }

    @Override
    public long insert(Customer customer) {
        long id = 0;

        db.beginTransaction();

        try {
            id = db.insert(TABLE, null, getContentValues(customer));

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return id;
    }

    @Override
    public void update(Customer customer) {
        if (db.inTransaction()) {
            db.beginTransaction();

            try {
                db.update(TABLE, getContentValues(customer), COLUMN_ID + " = ?", new String[]{ String.valueOf(customer.getId()) });

                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        } else {
            db.update(TABLE, getContentValues(customer), COLUMN_ID + " = ?", new String[]{ String.valueOf(customer.getId()) });
        }
    }

    @Override
    public void delete(Customer customer) {
        String query = "UPDATE " + TABLE
                + " SET "
                + COLUMN_DELETED + " = 1"
                + " WHERE "
                + COLUMN_ID + " = " + customer.getId();

        db.beginTransaction();

        try {
            db.execSQL(query);

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public Customer select(long id) {
        Customer customer = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                + COLUMN_ID + " = ? AND "
                + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            customer = getCursorValues(cursor);
        }
        cursor.close();

        return customer;
    }

    @Override
    public List<Customer> select(Map<String, String[]> parameters) {
        List<Customer> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE "
                    + SQLWhereClauseParser.parse(parameters, CustomerDAO.class) + " AND "
                    + COLUMN_DELETED + " = ?";

            Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(0) });
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (SQLWhereClauseException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Customer> selectAll() {
        List<Customer> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(0) });
        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public void persist(Customer customer) {
        if (select(customer.getId()) == null) {
            insert(customer);
        } else {
            update(customer);
        }
    }
}
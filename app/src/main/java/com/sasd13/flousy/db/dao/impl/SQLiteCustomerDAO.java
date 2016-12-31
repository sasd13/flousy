package com.sasd13.flousy.db.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.db.dao.ICustomerDAO;
import com.sasd13.flousy.db.condition.CustomerConditionExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteCustomerDAO extends SQLiteEntityDAO<Customer> implements ICustomerDAO, IPersistable<Customer> {

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
        long id = db.insert(TABLE, null, getContentValues(customer));

        customer.setId(id);

        return id;
    }

    @Override
    public void update(Customer customer) {
        db.update(TABLE, getContentValues(customer), COLUMN_ID + " = ?", new String[]{ String.valueOf(customer.getId()) });
    }

    @Override
    public void delete(Customer customer) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(TABLE);
        builder.append(" SET ");
        builder.append(COLUMN_DELETED + " = 1");
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = " + customer.getId());

        db.execSQL(builder.toString());
    }

    @Override
    public Customer select(long id) {
        Customer customer = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(id), String.valueOf(0) });
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
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ");
            builder.append(TABLE);
            builder.append(" WHERE ");
            builder.append(ConditionBuilder.parse(parameters, CustomerConditionExpression.class));
            builder.append(" AND ");
            builder.append(COLUMN_DELETED + " = ?");

            Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (ConditionBuilderException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Customer> selectAll() {
        List<Customer> list = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
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
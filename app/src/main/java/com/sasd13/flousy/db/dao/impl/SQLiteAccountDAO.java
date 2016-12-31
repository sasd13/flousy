package com.sasd13.flousy.db.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.db.dao.IAccountDAO;
import com.sasd13.flousy.db.condition.AccountConditionExpression;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteAccountDAO extends SQLiteEntityDAO<Account> implements IAccountDAO, IPersistable<Account> {

    @Override
    protected ContentValues getContentValues(Account account) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATEOPENING, String.valueOf(account.getDateOpening()));
        values.put(COLUMN_CUSTOMER_ID, account.getCustomer().getId());

        return values;
    }

    @Override
    protected Account getCursorValues(Cursor cursor) {
        Customer customer = new Customer();
        customer.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_CUSTOMER_ID)));

        Account account = new Account(customer);
        account.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        account.setDateOpening(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_DATEOPENING))));

        return account;
    }

    @Override
    public long insert(Account account) {
        long id = db.insert(TABLE, null, getContentValues(account));

        account.setId(id);

        return id;
    }

    @Override
    public void update(Account account) {
        db.update(TABLE, getContentValues(account), COLUMN_ID + " = ?", new String[]{ String.valueOf(account.getId()) });
    }

    @Override
    public void delete(Account account) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(TABLE);
        builder.append(" SET ");
        builder.append(COLUMN_DELETED + " = 1");
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = " + account.getId());

        db.execSQL(builder.toString());
    }

    @Override
    public Account select(long id) {
        Account account = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            account = getCursorValues(cursor);
        }
        cursor.close();

        return account;
    }

    @Override
    public List<Account> select(Map<String, String[]> parameters) {
        List<Account> list = new ArrayList<>();

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ");
            builder.append(TABLE);
            builder.append(" WHERE ");
            builder.append(ConditionBuilder.parse(parameters, AccountConditionExpression.class));
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
    public List<Account> selectAll() {
        List<Account> list = new ArrayList<>();

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
    public void persist(Account account) {
        if (select(account.getId()) == null) {
            insert(account);
        } else {
            update(account);
        }
    }
}
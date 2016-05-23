package com.sasd13.flousy.dao.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.dao.AccountDAO;
import com.sasd13.flousy.dao.IPersistable;
import com.sasd13.flousy.dao.db.util.SQLWhereClauseException;
import com.sasd13.flousy.dao.db.util.SQLWhereClauseParser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteAccountDAO extends SQLiteEntityDAO<Account> implements AccountDAO, IPersistable<Account> {

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
        return db.insert(TABLE, null, getContentValues(account));
    }

    @Override
    public void update(Account account) {
        db.update(TABLE, getContentValues(account), COLUMN_ID + " = ?", new String[]{ String.valueOf(account.getId()) });
    }

    @Override
    public void delete(Account account) {
        String query = "UPDATE " + TABLE
                + " SET "
                    + COLUMN_DELETED + " = 1"
                + " WHERE "
                    + COLUMN_ID + " = " + account.getId();

        db.execSQL(query);
    }

    @Override
    public Account select(long id) {
        Account account = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ? AND "
                    + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(id), String.valueOf(0) });
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
            String query = "SELECT * FROM " + TABLE
                    + " WHERE "
                        + SQLWhereClauseParser.parse(parameters, AccountDAO.class) + " AND "
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
    public List<Account> selectAll() {
        List<Account> list = new ArrayList<>();

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
    public void persist(Account account) {
        if (select(account.getId()) == null) {
            insert(account);
        } else {
            update(account);
        }
    }
}
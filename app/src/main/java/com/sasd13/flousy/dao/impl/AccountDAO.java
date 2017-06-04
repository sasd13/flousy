package com.sasd13.flousy.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.flousy.dao.IAccountDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountDAO extends AbstractDAO<Account> implements IAccountDAO {

    public AccountDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected ContentValues getContentValues(Account account) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, account.getId());
        values.put(COLUMN_CODE, account.getAccountID());
        values.put(COLUMN_DATEOPENING, String.valueOf(account.getDateOpening().getTime()));
        values.put(COLUMN_CUSTOMER, account.getCustomer().getIntermediary());

        return values;
    }

    @Override
    protected Account getCursorValues(Cursor cursor) {
        Account account = new Account();

        account.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        account.setAccountID(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));
        account.setDateOpening(new Date(Long.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_DATEOPENING)))));

        Customer customer = new Customer();
        customer.setIntermediary(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER)));
        account.setCustomer(customer);

        return account;
    }

    @Override
    public long create(Account account) {
        long id = db.insert(TABLE, null, getContentValues(account));

        account.setId(id);

        return id;
    }

    @Override
    public void update(Account account) {
        db.update(TABLE, getContentValues(account), COLUMN_ID + " = ?", new String[]{String.valueOf(account.getId())});
    }

    @Override
    public Account read(String accountID) {
        Account result = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_CODE + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{accountID});
        if (cursor.moveToNext()) {
            result = getCursorValues(cursor);
        }
        cursor.close();

        return result;
    }

    @Override
    public List<Account> readAll(String intermediary) {
        List<Account> results = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_CUSTOMER + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{intermediary});
        while (cursor.moveToNext()) {
            results.add(getCursorValues(cursor));
        }
        cursor.close();

        return results;
    }
}
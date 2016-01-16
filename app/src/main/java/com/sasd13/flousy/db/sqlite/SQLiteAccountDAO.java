package com.sasd13.flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;
import java.util.List;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.db.AccountDAO;

public class SQLiteAccountDAO extends SQLiteEntityDAO<Account> implements AccountDAO {

    @Override
    protected ContentValues getContentValues(Account account) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATEOPENING, String.valueOf(account.getDateOpening()));
        values.put(COLUMN_CLOSED, account.isClosed());
        values.put(COLUMN_CUSTOMER_ID, account.getCustomer().getId());

        return values;
    }

    @Override
    protected Account getCursorValues(Cursor cursor) {
        Account account = new Account();

        account.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        account.setDateOpening(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_DATEOPENING))));
        account.setClosed(cursor.getInt(cursor.getColumnIndex(COLUMN_CLOSED)) == 1);

        Customer customer = new Customer();
        customer.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_CUSTOMER_ID)));
        account.setCustomer(customer);

        return account;
    }

    @Override
    public long insert(Account account) {
        long id = executeInsert(TABLE, account);

        account.setId(id);

        return id;
    }

    @Override
    public void update(Account account) {
        executeUpdate(TABLE, account, COLUMN_ID, account.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(TABLE, COLUMN_ID, id);
    }

    @Override
    public Account select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Account> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
    }

    @Override
    public Account selectByCustomer(long customerId) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_CUSTOMER_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(customerId)});

        return getSingleResult(cursor);
    }
}
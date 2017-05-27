package com.sasd13.flousy.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.EnumOperationType;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.dao.IOperationDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO extends AbstractDAO<Operation> implements IOperationDAO {

    public OperationDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected ContentValues getContentValues(Operation operation) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_CODE, operation.getOperationID());
        values.put(COLUMN_DATEREALIZATION, String.valueOf(operation.getDateRealization().getTime()));
        values.put(COLUMN_TITLE, operation.getTitle());
        values.put(COLUMN_AMOUNT, operation.getAmount());
        values.put(COLUMN_TYPE, operation.getType().getCode());
        values.put(COLUMN_ACCOUNT, operation.getAccount().getAccountID());

        return values;
    }

    @Override
    protected Operation getCursorValues(Cursor cursor) {
        Operation operation = new Operation();

        operation.setOperationID(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));
        operation.setDateRealization(new Date(Long.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_DATEREALIZATION)))));
        operation.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        operation.setAmount(cursor.getDouble(cursor.getColumnIndex(COLUMN_AMOUNT)));
        operation.setType(EnumOperationType.find(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE))));

        Account account = new Account();
        account.setAccountID(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT)));
        operation.setAccount(account);

        return operation;
    }

    @Override
    public long create(Operation operation) {
        return db.insert(TABLE, null, getContentValues(operation));
    }

    @Override
    public void update(Operation operation) {
        db.update(TABLE, getContentValues(operation), COLUMN_CODE + " = ?", new String[]{String.valueOf(operation.getOperationID())});
    }

    @Override
    public void delete(Operation operation) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(TABLE);
        builder.append(" SET ");
        builder.append(COLUMN_FLAG_DELETED + " = 1");
        builder.append(" WHERE ");
        builder.append(COLUMN_CODE + " = " + operation.getOperationID());

        db.execSQL(builder.toString());
    }

    @Override
    public List<Operation> read(String accountID) {
        List<Operation> list = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ACCOUNT + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_FLAG_DELETED + " = 0");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{accountID});
        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
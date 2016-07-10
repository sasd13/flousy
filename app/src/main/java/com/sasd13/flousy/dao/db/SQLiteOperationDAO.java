package com.sasd13.flousy.dao.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.dao.IPersistable;
import com.sasd13.flousy.dao.OperationDAO;
import com.sasd13.flousy.dao.db.util.SQLWhereClauseException;
import com.sasd13.flousy.dao.db.util.SQLWhereClauseParser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteOperationDAO extends SQLiteEntityDAO<Operation> implements OperationDAO, IPersistable<Operation> {

    @Override
    protected ContentValues getContentValues(Operation operation) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATEREALIZATION, String.valueOf(operation.getDateRealization()));
        values.put(COLUMN_TITLE, operation.getTitle());
        values.put(COLUMN_AMOUNT, operation.getAmount());
        values.put(COLUMN_ACCOUNT_ID, operation.getAccount().getId());

        return values;
    }

    @Override
    protected Operation getCursorValues(Cursor cursor) {
        Account account = new Account();
        account.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ACCOUNT_ID)));

        Operation operation = new Operation(account);
        operation.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        operation.setDateRealization(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_DATEREALIZATION))));
        operation.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        operation.setAmount(cursor.getDouble(cursor.getColumnIndex(COLUMN_AMOUNT)));

        return operation;
    }

    @Override
    public long insert(Operation operation) {
        long id = db.insert(TABLE, null, getContentValues(operation));

        operation.setId(id);

        return id;
    }

    @Override
    public void update(Operation operation) {
        db.update(TABLE, getContentValues(operation), COLUMN_ID + " = ?", new String[]{ String.valueOf(operation.getId()) });
    }

    @Override
    public void delete(Operation operation) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(TABLE);
        builder.append(" SET ");
        builder.append(COLUMN_DELETED + " = 1");
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = " + operation.getId());

        db.execSQL(builder.toString());
    }

    @Override
    public Operation select(long id) {
        Operation operation = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            operation = getCursorValues(cursor);
        }
        cursor.close();

        return operation;
    }

    @Override
    public List<Operation> select(Map<String, String[]> parameters) {
        List<Operation> list = new ArrayList<>();

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ");
            builder.append(TABLE);
            builder.append(" WHERE ");
            builder.append(SQLWhereClauseParser.parse(parameters, OperationDAO.class));
            builder.append(" AND ");
            builder.append(COLUMN_DELETED + " = ?");

            Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
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
    public List<Operation> selectAll() {
        List<Operation> list = new ArrayList<>();

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
    public void persist(Operation operation) {
        if (select(operation.getId()) == null) {
            insert(operation);
        } else {
            update(operation);
        }
    }
}
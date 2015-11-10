package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;

import flousy.beans.core.Account;
import flousy.beans.core.ListOperations;
import flousy.beans.core.Operation;
import flousy.beans.core.OperationType;
import flousy.db.OperationTableAccessor;

/**
 * Created by Samir on 02/04/2015.
 */
class OperationDAO extends SQLiteTableDAO<Operation> implements OperationTableAccessor {

    @Override
    protected ContentValues getContentValues(Operation operation) {
        ContentValues values = new ContentValues();

        values.put(OPERATION_ID, operation.getId());
        values.put(OPERATION_DATE, String.valueOf(operation.getDate()));
        values.put(OPERATION_TYPE, String.valueOf(operation.getType()));
        values.put(OPERATION_NAME, operation.getName());
        values.put(OPERATION_VALUE, operation.getValue());

        return values;
    }

    @Override
    protected Operation getCursorValues(Cursor cursor) {
        Operation operation = new Operation();

        operation.setId(cursor.getLong(cursor.getColumnIndex(OPERATION_ID)));
        operation.setDate(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(OPERATION_DATE))));
        operation.setType(OperationType.valueOf(cursor.getString(cursor.getColumnIndex(OPERATION_TYPE))));
        operation.setName(cursor.getString(cursor.getColumnIndex(OPERATION_NAME)));
        operation.setValue(cursor.getLong(cursor.getColumnIndex(OPERATION_VALUE)));

        return operation;
    }

    @Override
    public long insert(Operation operation) {
        return 0;
    }

    @Override
    public long insert(Operation operation, Account account) {
        ContentValues values = getContentValues(operation);
        values.put(ACCOUNTS_ACCOUNT_ID, account.getId());

        return getDB().insert(OPERATION_TABLE_NAME, null, values);
    }

    @Override
    public void update(Operation operation) {
        getDB().update(OPERATION_TABLE_NAME, getContentValues(operation), OPERATION_ID + " = ?", new String[]{String.valueOf(operation.getId())});
    }

    @Override
    public void delete(Operation operation) {
        getDB().delete(OPERATION_TABLE_NAME, OPERATION_ID + " = ?", new String[]{String.valueOf(operation.getId())});
    }

    @Override
    public Operation select(long id) {
        Operation operation = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + OPERATION_TABLE_NAME
                        + " where " + OPERATION_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            operation = getCursorValues(cursor);
        }
        cursor.close();

        return operation;
    }

    @Override
    public ListOperations selectByAccount(long accountId) {
        return selectByAccount(accountId, false);
    }

    @Override
    public ListOperations selectByAccount(long accountId, boolean ascOrdered) {
        ListOperations listITrafficOperations = new ListOperations();

        String order = ascOrdered ? "ASC" : "DESC";

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + OPERATION_TABLE_NAME
                        + " where " + ACCOUNTS_ACCOUNT_ID + " = ?"
                        + " order by " + OPERATION_DATE + " " + order, new String[]{String.valueOf(accountId)});

        while (cursor.moveToNext()) {
            listITrafficOperations.add(getCursorValues(cursor));
        }
        cursor.close();

        return listITrafficOperations;
    }
}

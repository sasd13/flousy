package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;

import flousy.bean.trading.ITradingAccount;
import flousy.bean.trading.ListTrafficOperations;
import flousy.bean.trading.TradingException;
import flousy.bean.trading.ITrafficOperation;
import flousy.bean.trading.TrafficOperationFactory;
import flousy.db.OperationTableAccessor;

/**
 * Created by Samir on 02/04/2015.
 */
class OperationDAO extends SQLiteTableDAO<ITrafficOperation> implements OperationTableAccessor {

    @Override
    protected ContentValues getContentValues(ITrafficOperation trafficOperation) {
        ContentValues values = new ContentValues();

        values.put(OPERATION_ID, trafficOperation.getId());
        values.put(OPERATION_DATE, String.valueOf(trafficOperation.getDate()));
        values.put(OPERATION_TYPE, trafficOperation.getTrafficType());
        values.put(OPERATION_NAME, trafficOperation.getName());
        values.put(OPERATION_VALUE, trafficOperation.getValue());

        return values;
    }

    @Override
    protected ITrafficOperation getCursorValues(Cursor cursor) {
        ITrafficOperation trafficOperation = null;

        String trafficOperationType = cursor.getString(cursor.getColumnIndex(OPERATION_TYPE));

        try {
            trafficOperation = TrafficOperationFactory.create(trafficOperationType);

            trafficOperation.setId(cursor.getLong(cursor.getColumnIndex(OPERATION_ID)));
            trafficOperation.setDate(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(OPERATION_DATE))));
            trafficOperation.setName(cursor.getString(cursor.getColumnIndex(OPERATION_NAME)));
            trafficOperation.setValue(cursor.getLong(cursor.getColumnIndex(OPERATION_VALUE)));
        } catch (TradingException e) {
            e.printStackTrace();
        }

        return trafficOperation;
    }

    @Override
    public long insert(ITrafficOperation trafficOperation) {
        return 0;
    }

    @Override
    public long insert(ITrafficOperation trafficOperation, ITradingAccount tradingAccount) {
        ContentValues values = getContentValues(trafficOperation);
        values.put(ACCOUNTS_ACCOUNT_ID, tradingAccount.getId());

        return getDB().insert(OPERATION_TABLE_NAME, null, values);
    }

    @Override
    public void update(ITrafficOperation trafficOperation) {
        getDB().update(OPERATION_TABLE_NAME, getContentValues(trafficOperation), OPERATION_ID + " = ?", new String[]{String.valueOf(trafficOperation.getId())});
    }

    @Override
    public void delete(ITrafficOperation trafficOperation) {
        getDB().delete(OPERATION_TABLE_NAME, OPERATION_ID + " = ?", new String[]{String.valueOf(trafficOperation.getId())});
    }

    @Override
    public ITrafficOperation select(long id) {
        ITrafficOperation trafficOperation = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + OPERATION_TABLE_NAME
                        + " where " + OPERATION_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            trafficOperation = getCursorValues(cursor);
        }
        cursor.close();

        return trafficOperation;
    }

    @Override
    public ListTrafficOperations selectByAccount(long accountId) {
        return selectByAccount(accountId, false);
    }

    @Override
    public ListTrafficOperations selectByAccount(long accountId, boolean ascOrdered) {
        ListTrafficOperations listITrafficOperations = new ListTrafficOperations();

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

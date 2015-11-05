package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;

import flousy.bean.trading.ITradingAccount;
import flousy.bean.trading.ListTrafficOperations;
import flousy.bean.trading.TradingException;
import flousy.bean.trading.TrafficFactory;
import flousy.bean.trading.TrafficOperation;
import flousy.db.OperationTableAccessor;

/**
 * Created by Samir on 02/04/2015.
 */
class OperationDAO extends SQLiteTableDAO<TrafficOperation> implements OperationTableAccessor {

    @Override
    protected ContentValues getContentValues(TrafficOperation trafficOperation) {
        ContentValues values = new ContentValues();

        values.put(OPERATION_ID, trafficOperation.getId());
        values.put(OPERATION_DATE, trafficOperation.getDate().toString());
        values.put(OPERATION_TYPE, trafficOperation.getTrafficType());
        values.put(OPERATION_VALUE, trafficOperation.getValue());

        return values;
    }

    @Override
    protected TrafficOperation extractCursorValues(Cursor cursor) {
        TrafficOperation trafficOperation = null;

        String trafficType = cursor.getString(cursor.getColumnIndex(OPERATION_TYPE));

        try {
            trafficOperation = (TrafficOperation) TrafficFactory.create(trafficType);

            trafficOperation.setId(cursor.getLong(cursor.getColumnIndex(OPERATION_ID)));
            trafficOperation.setDate(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(OPERATION_DATE))));
            trafficOperation.setValue(cursor.getLong(cursor.getColumnIndex(OPERATION_VALUE)));
        } catch (TradingException e) {
            e.printStackTrace();
        }

        return trafficOperation;
    }

    @Override
    public long insert(TrafficOperation trafficOperation) {
        return 0;
    }

    @Override
    public long insert(TrafficOperation trafficOperation, ITradingAccount tradingAccount) {
        ContentValues values = getContentValues(trafficOperation);
        values.put(ACCOUNTS_ACCOUNT_ID, tradingAccount.getId());

        return db.insert(OPERATION_TABLE_NAME, null, values);
    }

    @Override
    public void update(TrafficOperation trafficOperation) {
        db.update(OPERATION_TABLE_NAME, getContentValues(trafficOperation), OPERATION_ID + " = ?", new String[]{String.valueOf(trafficOperation.getId())});
    }

    @Override
    public void delete(TrafficOperation trafficOperation) {
        db.delete(OPERATION_TABLE_NAME, OPERATION_ID + " = ?", new String[]{String.valueOf(trafficOperation.getId())});
    }

    @Override
    public TrafficOperation select(long id) {
        TrafficOperation trafficOperation = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + OPERATION_TABLE_NAME
                        + " where " + OPERATION_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            trafficOperation = extractCursorValues(cursor);
        }
        cursor.close();

        return trafficOperation;
    }

    @Override
    public ListTrafficOperations selectAll() {
        ListTrafficOperations listTrafficOperations = new ListTrafficOperations();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + OPERATION_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            listTrafficOperations.add(extractCursorValues(cursor));
        }
        cursor.close();

        return listTrafficOperations;
    }
}

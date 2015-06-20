package flousy.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

import flousy.content.finance.Income;
import flousy.content.finance.ListIncomes;
import flousy.content.finance.Periodicity;

/**
 * Created by Samir on 02/04/2015.
 */
class IncomeDAO extends AbstractTableDAO {

    public static final String INCOME_TABLE_NAME = "incomes";

    public static final String INCOME_ID = "income_id";
    public static final String INCOME_VALUE = "income_value";
    public static final String INCOME_START_DATE = "income_start_date";
    public static final String INCOME_END_DATE = "income_end_date";
    public static final String INCOME_PERIODICITY = "income_periodicity";
    public static final String INCOME_CLIENT_ID = "income_client_id";

    public long insert(Income income, String clientId) {
        ContentValues values = getContentValues(income);
        values.put(INCOME_CLIENT_ID, clientId);

        return db.insert(INCOME_TABLE_NAME, null, values);
    }

    private ContentValues getContentValues(Income income) {
        ContentValues values = new ContentValues();

        values.put(INCOME_ID, income.getId());
        values.put(INCOME_VALUE, income.getValue());
        values.put(INCOME_START_DATE, income.getStartDate().getTime());
        values.put(INCOME_END_DATE, income.getEndDate().getTime());
        values.put(INCOME_PERIODICITY, income.getPeriodicity().toString());

        return values;
    }

    public long update(Income income) {
        return db.update(INCOME_TABLE_NAME, getContentValues(income), INCOME_ID + " = ?", new String[]{income.getId()});
    }

    public long delete(Income income) {
        return db.delete(INCOME_TABLE_NAME, INCOME_ID + " = ?", new String[]{income.getId()});
    }

    public Income select(String incomeId) {
        Income income = null;

        Cursor cursor = db.rawQuery(
                "select " + INCOME_VALUE + ", " + INCOME_START_DATE + ", " + INCOME_END_DATE + ", " + INCOME_PERIODICITY
                        + " from " + INCOME_TABLE_NAME
                        + " where " + INCOME_ID + " = ?", new String[]{incomeId});

        if (cursor.moveToNext()) {
            income = new Income();
            income.setId(incomeId);
            income.setValue(cursor.getLong(0));
            income.setStartDate(new Date(cursor.getLong(1)));
            income.setEndDate(new Date(cursor.getLong(2)));
            income.setPeriodicity(Periodicity.valueOf(cursor.getString(3)));
        }
        cursor.close();

        return income;
    }

    public ListIncomes selectAllOfClient(String clientId) {
        ListIncomes listIncomes = new ListIncomes();

        Cursor cursor = db.rawQuery(
                "select " + INCOME_ID + ", " + INCOME_VALUE + ", " + INCOME_START_DATE + ", " + INCOME_END_DATE + ", " + INCOME_PERIODICITY
                        + " from " + INCOME_TABLE_NAME
                        + " where " + INCOME_CLIENT_ID + " = ?", new String[]{clientId});

        Income income;

        while (cursor.moveToNext()) {
            income = new Income();
            income.setId(cursor.getString(0));
            income.setValue(cursor.getLong(1));
            income.setStartDate(new Date(cursor.getLong(2)));
            income.setEndDate(new Date(cursor.getLong(3)));
            income.setPeriodicity(Periodicity.valueOf(cursor.getString(4)));

            listIncomes.add(income);
        }
        cursor.close();

        return listIncomes;
    }

    public boolean contains(String incomeId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + INCOME_ID
                        + " from " + INCOME_TABLE_NAME
                        + " where " + INCOME_ID + " = ?", new String[]{incomeId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}

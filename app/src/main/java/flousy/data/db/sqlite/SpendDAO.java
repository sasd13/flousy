package flousy.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import flousy.content.spend.ListSpends;
import flousy.content.spend.Spend;

/**
 * Created by Samir on 02/04/2015.
 */
class SpendDAO extends AbstractTableDAO {

    public static final String SPEND_TABLE_NAME = "spends";

    public static final String SPEND_ID = "spend_id";
    public static final String SPEND_DATE = "spend_date";
    public static final String SPEND_ACCOUNT_ID = "spend_account_id";

    public long insert(Spend spend, String spendsAccountId) {
        ContentValues values = getContentValues(spend);
        values.put(SPEND_ACCOUNT_ID, spendsAccountId);

        return db.insert(SPEND_TABLE_NAME, null, values);
    }

    private ContentValues getContentValues(Spend spend) {
        ContentValues values = new ContentValues();

        values.put(SPEND_ID, spend.getId());
        values.put(SPEND_DATE, spend.getDate().getTime());

        return values;
    }

    public long update(Spend spend) {
        return db.update(SPEND_TABLE_NAME, getContentValues(spend), SPEND_ID + " = ?", new String[]{spend.getId()});
    }

    public long delete(Spend spend) {
        return db.delete(SPEND_TABLE_NAME, SPEND_ID + " = ?", new String[]{spend.getId()});
    }

    public Spend select(String spendId) {
        Spend spend = null;

        Cursor cursor = db.rawQuery(
                "select " + SPEND_DATE
                        + " from " + SPEND_TABLE_NAME
                        + " where " + SPEND_ID + " = ?", new String[]{spendId});

        if (cursor.moveToNext()) {
            spend = new Spend();
            spend.setId(spendId);
            spend.setDate(new Date(cursor.getLong(0)));
        }
        cursor.close();

        try {
            spend.setListArticles(SQLiteDAO.getInstance().selectArticlesOfSpend(spendId));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return spend;
    }

    public ListSpends selectAllOfSpendsAccount(String spendsAccountId) {
        ListSpends listSpends = new ListSpends();

        List<String> listIds = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "select " + SPEND_ID
                        + " from " + SPEND_TABLE_NAME
                        + " where " + SPEND_ACCOUNT_ID + " = ?", new String[]{spendsAccountId});

        while (cursor.moveToNext()) {
            listIds.add(cursor.getString(0));
        }
        cursor.close();

        for (Object id : listIds) {
            listSpends.add(select((String) id));
        }

        return listSpends;
    }

    public boolean contains(String spendId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + SPEND_ID
                        + " from " + SPEND_TABLE_NAME
                        + " where " + SPEND_ID + " = ?", new String[]{spendId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}

package com.sasd13.flousy.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.flousy.dao.IPreferenceDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 03/06/2017.
 */

public class PreferenceDAO extends AbstractDAO<Preference> implements IPreferenceDAO {

    public PreferenceDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected ContentValues getContentValues(Preference preference) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, preference.getId());
        values.put(COLUMN_CATEGORY, preference.getCategory());
        values.put(COLUMN_NAME, preference.getName());
        values.put(COLUMN_DEFAULTVALUE, preference.getDefaultValue());

        return values;
    }

    @Override
    protected Preference getCursorValues(Cursor cursor) {
        Preference preference = new Preference();

        preference.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        preference.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
        preference.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        preference.setDefaultValue(cursor.getString(cursor.getColumnIndex(COLUMN_DEFAULTVALUE)));

        return preference;
    }

    @Override
    public List<Preference> readAll() {
        List<Preference> results = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);

        Cursor cursor = db.rawQuery(builder.toString(), null);
        while (cursor.moveToNext()) {
            results.add(getCursorValues(cursor));
        }
        cursor.close();

        return results;
    }
}

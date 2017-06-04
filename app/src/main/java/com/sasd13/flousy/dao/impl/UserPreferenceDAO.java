package com.sasd13.flousy.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.flousy.dao.IPreferenceDAO;
import com.sasd13.flousy.dao.IUserDAO;
import com.sasd13.flousy.dao.IUserPreferenceDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 03/06/2017.
 */

public class UserPreferenceDAO extends AbstractDAO<UserPreference> implements IUserPreferenceDAO {

    private static final String USERS_TABLE_ALIAS = "usr";
    private static final String USERS_COLUMN_ID = USERS_TABLE_ALIAS + "." + IUserDAO.COLUMN_ID;
    private static final String USERS_COLUMN_USERID = USERS_TABLE_ALIAS + "." + IUserDAO.COLUMN_USERID;

    private static final String PREFERENCES_TABLE_ALIAS = "prf";
    private static final String PREFERENCES_COLUMN_ID = PREFERENCES_TABLE_ALIAS + "." + IPreferenceDAO.COLUMN_ID;
    private static final String PREFERENCES_COLUMN_CATEGORY = PREFERENCES_TABLE_ALIAS + "." + IPreferenceDAO.COLUMN_CATEGORY;
    private static final String PREFERENCES_COLUMN_NAME = PREFERENCES_TABLE_ALIAS + "." + IPreferenceDAO.COLUMN_NAME;
    private static final String PREFERENCES_COLUMN_DEFAULTVALUE = PREFERENCES_TABLE_ALIAS + "." + IPreferenceDAO.COLUMN_DEFAULTVALUE;

    public UserPreferenceDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected ContentValues getContentValues(UserPreference userPreference) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, userPreference.getId());
        values.put(COLUMN_VALUE, userPreference.getValue());
        values.put(COLUMN_USER, userPreference.getUserId());
        values.put(COLUMN_PREFERENCE, userPreference.getPreference().getId());

        return values;
    }

    @Override
    protected UserPreference getCursorValues(Cursor cursor) {
        UserPreference userPreference = new UserPreference();

        userPreference.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        userPreference.setValue(cursor.getString(cursor.getColumnIndex(COLUMN_VALUE)));
        userPreference.setUserId(cursor.getLong(cursor.getColumnIndex(COLUMN_USER)));

        Preference preference = new Preference();
        preference.setId(cursor.getLong(cursor.getColumnIndex(PREFERENCES_COLUMN_ID)));
        preference.setCategory(cursor.getString(cursor.getColumnIndex(PREFERENCES_COLUMN_CATEGORY)));
        preference.setName(cursor.getString(cursor.getColumnIndex(PREFERENCES_COLUMN_NAME)));
        preference.setDefaultValue(cursor.getString(cursor.getColumnIndex(PREFERENCES_COLUMN_DEFAULTVALUE)));
        userPreference.setPreference(preference);

        return userPreference;
    }

    @Override
    public void update(UserPreference userPreference) {
        db.update(TABLE, getContentValues(userPreference), COLUMN_ID + " = ?", new String[]{String.valueOf(userPreference.getId())});
    }

    @Override
    public List<UserPreference> read(String userID) {
        List<UserPreference> results = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        builder.append(COLUMN_ID);
        builder.append(", " + COLUMN_VALUE);
        builder.append(", " + COLUMN_USER);
        builder.append(", " + PREFERENCES_COLUMN_ID);
        builder.append(", " + PREFERENCES_COLUMN_CATEGORY);
        builder.append(", " + PREFERENCES_COLUMN_NAME);
        builder.append(", " + PREFERENCES_COLUMN_DEFAULTVALUE);
        builder.append(" FROM ");
        builder.append(TABLE);
        builder.append(" INNER JOIN ");
        builder.append(IUserDAO.TABLE + " " + USERS_TABLE_ALIAS);
        builder.append(" ON ");
        builder.append(COLUMN_USER + " = " + USERS_COLUMN_ID);
        builder.append(" INNER JOIN ");
        builder.append(IPreferenceDAO.TABLE + " " + PREFERENCES_TABLE_ALIAS);
        builder.append(" ON ");
        builder.append(COLUMN_PREFERENCE + " = " + PREFERENCES_COLUMN_ID);
        builder.append(" WHERE ");
        builder.append(USERS_COLUMN_USERID + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{userID});
        while (cursor.moveToNext()) {
            results.add(getCursorValues(cursor));
        }
        cursor.close();

        return results;
    }
}

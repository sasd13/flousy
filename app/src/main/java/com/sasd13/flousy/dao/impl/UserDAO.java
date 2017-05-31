package com.sasd13.flousy.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.bean.user.UserCreate;
import com.sasd13.flousy.bean.user.UserUpdate;
import com.sasd13.flousy.dao.IUserDAO;
import com.sasd13.javaex.security.Credential;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    public UserDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected ContentValues getContentValues(User user) {
        return null;
    }

    protected ContentValues getContentValues(UserCreate userCreate) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERID, "");
        values.put(COLUMN_USERNAME, userCreate.getCredential().getUsername());
        values.put(COLUMN_PASSWORD, userCreate.getCredential().getPassword());
        values.put(COLUMN_INTERMEDIARY, userCreate.getUser().getIntermediary());

        return values;
    }

    protected ContentValues getContentValues(UserUpdate userUpdate) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERID, userUpdate.getUser().getUserID());
        values.put(COLUMN_USERNAME, userUpdate.getCredentials().getCurrent().getUsername());
        values.put(COLUMN_PASSWORD, userUpdate.getCredentials().getCurrent().getPassword());
        values.put(COLUMN_INTERMEDIARY, userUpdate.getUser().getIntermediary());

        return values;
    }

    @Override
    protected User getCursorValues(Cursor cursor) {
        User user = new User();

        user.setUserID(cursor.getString(cursor.getColumnIndex(COLUMN_USERID)));
        user.setIntermediary(cursor.getString(cursor.getColumnIndex(COLUMN_INTERMEDIARY)));

        return user;
    }

    @Override
    public long create(UserCreate userCreate) {
        return db.insert(TABLE, null, getContentValues(userCreate));
    }

    @Override
    public void update(UserUpdate userUpdate) {
        db.update(TABLE, getContentValues(userUpdate), COLUMN_USERID + " = ?", new String[]{String.valueOf(userUpdate.getUser().getUserID())});
    }

    @Override
    public User find(Credential credential) {
        User user = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_USERNAME + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_PASSWORD + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{credential.getUsername(), credential.getPassword()});
        if (cursor.moveToNext()) {
            user = getCursorValues(cursor);
        }
        cursor.close();

        return user;
    }

    @Override
    public User find(String userID) {
        User user = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_USERID + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{userID});
        if (cursor.moveToNext()) {
            user = getCursorValues(cursor);
        }
        cursor.close();

        return user;
    }
}
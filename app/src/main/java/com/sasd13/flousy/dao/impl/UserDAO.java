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

        values.put(COLUMN_USERNAME, userCreate.getCredential().getUsername());
        values.put(COLUMN_PASSWORD, userCreate.getCredential().getPassword());
        values.put(COLUMN_USERID, userCreate.getUser().getUserID());
        values.put(COLUMN_INTERMEDIARY, userCreate.getUser().getIntermediary());
        values.put(COLUMN_EMAIL, userCreate.getUser().getEmail());

        return values;
    }

    protected ContentValues getContentValues(UserUpdate userUpdate) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, userUpdate.getUser().getId());
        values.put(COLUMN_USERNAME, userUpdate.getCredentials().getCurrent().getUsername());
        values.put(COLUMN_PASSWORD, userUpdate.getCredentials().getCurrent().getPassword());
        values.put(COLUMN_USERID, userUpdate.getUser().getUserID());
        values.put(COLUMN_INTERMEDIARY, userUpdate.getUser().getIntermediary());
        values.put(COLUMN_EMAIL, userUpdate.getUser().getEmail());

        return values;
    }

    @Override
    protected User getCursorValues(Cursor cursor) {
        User user = new User();

        user.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        user.setUserID(cursor.getString(cursor.getColumnIndex(COLUMN_USERID)));
        user.setIntermediary(cursor.getString(cursor.getColumnIndex(COLUMN_INTERMEDIARY)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));

        return user;
    }

    @Override
    public long create(UserCreate userCreate) {
        long id = db.insert(TABLE, null, getContentValues(userCreate));

        userCreate.getUser().setId(id);

        return id;
    }

    @Override
    public void update(UserUpdate userUpdate) {
        db.update(TABLE, getContentValues(userUpdate), COLUMN_ID + " = ?", new String[]{String.valueOf(userUpdate.getUser().getId())});
    }

    @Override
    public User find(Credential credential) {
        User result = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_USERNAME + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_PASSWORD + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{credential.getUsername(), credential.getPassword()});
        if (cursor.moveToNext()) {
            result = getCursorValues(cursor);
        }
        cursor.close();

        return result;
    }

    @Override
    public User find(String userID) {
        User result = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_USERID + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{userID});
        if (cursor.moveToNext()) {
            result = getCursorValues(cursor);
        }
        cursor.close();

        return result;
    }
}
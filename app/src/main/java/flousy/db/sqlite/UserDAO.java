package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.beans.User;
import flousy.db.UserTableAccessor;

public class UserDAO extends SQLiteTableDAO<User> implements UserTableAccessor {

    public UserDAO(SQLiteDBHandler dbHandler) { super(dbHandler); }

    @Override
    protected ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();

        values.put(USER_FIRSTNAME, user.getFirstName());
        values.put(USER_LASTNAME, user.getLastName());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());

        return values;
    }

    @Override
    protected User getCursorValues(Cursor cursor) {
        User user = new User();

        user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_FIRSTNAME)));
        user.setLastName(cursor.getString(cursor.getColumnIndex(USER_LASTNAME)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));

        return user;
    }

    @Override
    public long insert(User user) {
        return getDB().insertOrThrow(USER_TABLE_NAME, null, getContentValues(user));
    }

    @Override
    public void update(User user) {
        getDB().update(USER_TABLE_NAME, getContentValues(user), USER_EMAIL + " = ?", new String[]{String.valueOf(user.getEmail())});
    }

    @Override
    public void delete(User user) {
        getDB().delete(USER_TABLE_NAME, USER_EMAIL + " = ?", new String[]{String.valueOf(user.getEmail())});
    }

    @Override
    public User select(String email) {
        User user = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + USER_TABLE_NAME
                        + " where " + USER_EMAIL + " = ?", new String[]{email});

        if (cursor.moveToNext()) {
            user = getCursorValues(cursor);
        }
        cursor.close();

        return user;
    }

    @Override
    public boolean containsByEmail(String email) {
        boolean contains = false;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + USER_TABLE_NAME
                        + " where " + USER_EMAIL + " = ?", new String[]{email});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}

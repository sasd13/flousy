package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.bean.user.User;
import flousy.db.UserTableAccessor;

/**
 * Created by Samir on 02/04/2015.
 */
class UserDAO extends SQLiteTableDAO<User> implements UserTableAccessor {

    @Override
    protected ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();

        values.put(USER_ID, user.getId());
        values.put(USER_FIRSTNAME, user.getFirstName());
        values.put(USER_LASTNAME, user.getLastName());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());

        return values;
    }

    @Override
    protected User extractCursorValues(Cursor cursor) {
        User user = new User();

        user.setId(cursor.getLong(cursor.getColumnIndex(USER_ID)));
        user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_FIRSTNAME)));
        user.setLastName(cursor.getString(cursor.getColumnIndex(USER_LASTNAME)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));

        return user;
    }

    @Override
    public long insert(User user) {
        return db.insert(USER_TABLE_NAME, null, getContentValues(user));
    }

    @Override
    public void update(User user) {
        db.update(USER_TABLE_NAME, getContentValues(user), USER_ID + " = ?", new String[]{String.valueOf(user.getId())});
    }

    @Override
    public void delete(User user) {
        db.delete(USER_TABLE_NAME, USER_ID + " = ?", new String[]{String.valueOf(user.getId())});
    }

    @Override
    public User select(long id) {
        User user = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + USER_TABLE_NAME
                        + " where " + USER_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            user = extractCursorValues(cursor);
        }
        cursor.close();

        return user;
    }

    @Override
    public User selectByEmail(String email) {
        User user = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + USER_TABLE_NAME
                        + " where " + USER_EMAIL + " = ?", new String[]{email});

        if (cursor.moveToNext()) {
            user = extractCursorValues(cursor);
        }
        cursor.close();

        return user;
    }

    @Override
    public boolean contains(String email) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
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

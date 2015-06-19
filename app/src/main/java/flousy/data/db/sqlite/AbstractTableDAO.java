package flousy.data.db.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Samir on 05/06/2015.
 */
abstract class AbstractTableDAO {

    protected SQLiteDatabase db;

    protected AbstractTableDAO() {}

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
}

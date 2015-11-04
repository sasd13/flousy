package flousy.db;

import flousy.db.sqlite.SQLiteDAO;

/**
 * Created by Samir on 11/06/2015.
 */
class DBAccessorFactory {

    private DBAccessorFactory() {}

    public static DBAccessor create(String dbType) {
        switch (dbType) {
            case "SQLITE": default:
                return SQLiteDAO.getInstance();
        }
    }
}

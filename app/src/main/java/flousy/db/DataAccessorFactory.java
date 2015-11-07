package flousy.db;

import flousy.db.sqlite.SQLiteDAO;

/**
 * Created by Samir on 11/06/2015.
 */
class DataAccessorFactory {

    private DataAccessorFactory() {}

    public static DataAccessor create(String type) {
        if ("MYSQL".equalsIgnoreCase(type)) {

        } else if ("POSTGRESQL".equalsIgnoreCase(type)) {

        } else if ("ORACLE".equalsIgnoreCase(type)) {

        } else if ("SQLSERVER".equalsIgnoreCase(type)) {

        }

        return SQLiteDAO.getInstance();
    }
}

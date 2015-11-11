package flousy.db;

import flousy.db.DataAccessor;
import flousy.db.sqlite.SQLiteDAO;

/**
 * Created by Samir on 11/11/2015.
 */
public class DataAccessorFactory {

    private DataAccessorFactory() {}

    public static DataAccessor get() {
        return get("SQLITE");
    }

    public static DataAccessor get(String dbType) {
        if ("SQLITE".equalsIgnoreCase(dbType)) {
            return SQLiteDAO.getInstance();
        }

        return null;
    }
}

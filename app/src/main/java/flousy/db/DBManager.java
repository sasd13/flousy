package flousy.db;

import android.content.Context;

/**
 * Created by Samir on 11/06/2015.
 */
public class DBManager {

    public static final String DBTYPE_SQLITE = "SQLITE";

    private static DataAccessor dao;

    public static void start(Context context) {
        start(context, DBTYPE_SQLITE);
    }

    public static void start(Context context, String dbType) {
        dao = DataAccessorFactory.create(dbType);
        dao.init(context);
    }

    public static DataAccessor getDao() {
        return dao;
    }
}

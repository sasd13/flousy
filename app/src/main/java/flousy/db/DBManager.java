package flousy.db;

import android.content.Context;

/**
 * Created by Samir on 11/06/2015.
 */
public class DBManager {

    private static final String DBTYPE_SQLITE = "SQLITE";

    private static DBAccessor dao;

    public static void start(Context context) {
        start(context, DBTYPE_SQLITE);
    }

    public static void start(Context context, String dbType) {
        dao = DBAccessorFactory.create(dbType);
        dao.init(context);
    }

    public static DBAccessor getDao() {
        return dao;
    }
}

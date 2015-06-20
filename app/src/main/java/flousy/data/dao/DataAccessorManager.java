package flousy.data.dao;

import android.content.Context;

import flousy.data.dao.accessor.DataAccessor;
import flousy.data.dao.accessor.DataAccessorException;
import flousy.data.dao.accessor.DataAccessorFactory;
import flousy.data.dao.accessor.DataAccessorType;
import flousy.data.db.sqlite.SQLiteDAO;

/**
 * Created by Samir on 11/06/2015.
 */
public class DataAccessorManager {

    private static DataAccessor dao;
    private static Context context;

    public static void start(Context context) {
        start(context, DataAccessorType.SQLITE);
    }

    public static void start(Context mContext, DataAccessorType type) {
        context = mContext;

        try {
            dao = DataAccessorFactory.get(type);

            config();
        } catch (DataAccessorException e) {
            e.printStackTrace();
        }
    }

    private static void config() {
        switch (dao.getType()) {
            case SQLITE:
                ((SQLiteDAO) dao).create(context);
                break;
        }
    }

    public static DataAccessor getDao() {
        return dao;
    }

    public static void setDao(DataAccessor mDao) {
        dao = mDao;
    }
}

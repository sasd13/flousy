package flousy.data.dao.accessor;

import flousy.data.db.sqlite.SQLiteDAO;

/**
 * Created by Samir on 11/06/2015.
 */
public class DataAccessorFactory {

    private DataAccessorFactory() {}

    public static DataAccessor get(DataAccessorType type) throws DataAccessorException {
        switch (type) {
            case SQLITE:
                return SQLiteDAO.getInstance();
            default:
                throw new DataAccessorException("Data accessor not found");
        }
    }
}

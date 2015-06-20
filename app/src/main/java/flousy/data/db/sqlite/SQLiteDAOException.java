package flousy.data.db.sqlite;

import flousy.data.dao.accessor.DataAccessorException;

/**
 * Created by Samir on 13/06/2015.
 */
public class SQLiteDAOException extends DataAccessorException {

    public SQLiteDAOException(String message) {
        super(message);
    }
}

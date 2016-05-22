package com.sasd13.flousy.dao.db.util;

/**
 * Created by Samir on 22/05/2016.
 */
import java.sql.SQLException;

public class SQLWhereClauseException extends SQLException {

    public SQLWhereClauseException(String message) {
        super(message);
    }
}
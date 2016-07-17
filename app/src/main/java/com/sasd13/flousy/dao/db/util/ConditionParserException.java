package com.sasd13.flousy.dao.db.util;

/**
 * Created by Samir on 22/05/2016.
 */
import java.sql.SQLException;

public class ConditionParserException extends SQLException {

    public ConditionParserException(String message) {
        super(message);
    }
}
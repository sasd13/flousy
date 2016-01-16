package com.sasd13.flousy.db;

import com.sasd13.flousy.db.sqlite.SQLiteDAO;
import com.sasd13.javaex.db.IDAO;

/**
 * Created by Samir on 13/01/2016.
 */
public class DAOFactory {

    public static IDAO make() {
        return make("SQLITE");
    }

    public static IDAO make(String dao) {
        if ("SQLITE".equalsIgnoreCase(dao)) {
            return SQLiteDAO.getInstance();
        } else {
            return null;
        }
    }
}

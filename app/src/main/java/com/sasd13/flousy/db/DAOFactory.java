package com.sasd13.flousy.db;

import com.sasd13.flousy.db.sqlite.SQLiteDAO;

public class DAOFactory {

    public static DAO make() {
        return make(SQLiteDAO.class);
    }

    public static DAO make(Class daoClass) {
        return SQLiteDAO.getInstance();
    }
}

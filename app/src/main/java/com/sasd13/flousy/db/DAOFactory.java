package com.sasd13.flousy.db;

import com.sasd13.flousy.db.sqlite.SQLiteDAO;

public class DAOFactory {

    public static DAO get() {
        return get(SQLiteDAO.class);
    }

    public static DAO get(Class daoClass) {
        return SQLiteDAO.getInstance();
    }
}
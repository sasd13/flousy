package com.sasd13.flousy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.flousy.dao.IAccountDAO;
import com.sasd13.flousy.dao.ICustomerDAO;
import com.sasd13.flousy.dao.IOperationDAO;
import com.sasd13.flousy.dao.IUserDAO;
import com.sasd13.flousy.dao.SQLiteDBHandler;
import com.sasd13.flousy.dao.SQLiteDBInformation;
import com.sasd13.flousy.dao.impl.AbstractDAO;
import com.sasd13.flousy.dao.impl.AccountDAO;
import com.sasd13.flousy.dao.impl.CustomerDAO;
import com.sasd13.flousy.dao.impl.OperationDAO;
import com.sasd13.flousy.dao.impl.UserDAO;

public class Repository {

    private Resolver resolver;
    private SQLiteDatabase db;

    public Repository(Resolver resolver, Context context) {
        this.resolver = resolver;
        db = (new SQLiteDBHandler(context, SQLiteDBInformation.DB, null, SQLiteDBInformation.VERSION)).getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public AbstractDAO getDAO(Class mClass) {
        AbstractDAO dao = (AbstractDAO) resolver.resolve(mClass);

        if (dao == null) {
            dao = make(mClass);

            resolver.register(mClass, dao);
        }

        return dao;
    }

    private AbstractDAO make(Class mClass) {
        if (IUserDAO.class.isAssignableFrom(mClass)) {
            return new UserDAO(db);
        } else if (ICustomerDAO.class.isAssignableFrom(mClass)) {
            return new CustomerDAO(db);
        } else if (IAccountDAO.class.isAssignableFrom(mClass)) {
            return new AccountDAO(db);
        } else if (IOperationDAO.class.isAssignableFrom(mClass)) {
            return new OperationDAO(db);
        } else {
            return null;
        }
    }
}
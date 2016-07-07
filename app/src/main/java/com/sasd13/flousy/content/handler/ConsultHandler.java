package com.sasd13.flousy.content.handler;

import android.content.Context;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class ConsultHandler {

    public static Account readAccount(Context context, long id) {
        LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.create(context));
        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.CUSTOMER.getName(), new String[] { String.valueOf(id) });

        return persistor.deepRead(parameters, Account.class).get(0);
    }
}

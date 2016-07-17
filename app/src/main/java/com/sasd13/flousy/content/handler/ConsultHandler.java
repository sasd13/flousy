package com.sasd13.flousy.content.handler;

import com.sasd13.flousy.ConsultActivity;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.EnumParameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class ConsultHandler {

    private ConsultActivity consultActivity;
    private LayeredPersistor persistor;
    private Map<String, String[]> parameters;

    public ConsultHandler(ConsultActivity consultActivity) {
        this.consultActivity = consultActivity;
        persistor = new LayeredPersistor(SQLiteDAO.create(consultActivity));
        parameters = new HashMap<>();
    }

    public Account readAccount() {
        long id = SessionHelper.getExtraIdFromSession(consultActivity, Extra.CUSTOMER_ID);

        parameters.clear();
        parameters.put(EnumParameter.CUSTOMER.getName(), new String[] { String.valueOf(id) });

        return persistor.deepRead(parameters, Account.class).get(0);
    }
}

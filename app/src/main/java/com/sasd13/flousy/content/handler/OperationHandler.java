package com.sasd13.flousy.content.handler;

import com.sasd13.flousy.OperationActivity;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.OperationForm;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class OperationHandler {

    public static final int TYPE_CREATE = 0;
    public static final int TYPE_UPDATE = 1;
    public static final int TYPE_DELETE = 2;

    private static LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.getInstance());

    public static Operation readOperation(long id) {
        return persistor.read(id, Operation.class);
    }

    public static void createOperation(OperationActivity operationActivity, OperationForm operationForm) {
        String[] errors = validFormInputs(operationForm);

        if (errors.length != 0) {
            operationActivity.onError(errors[0]);
        } else {
            Map<String, String[]> parameters = new HashMap<>();
            parameters.put(Parameter.CUSTOMER.getName(), new String[]{ String.valueOf(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID))});

            Account account = persistor.read(parameters, Account.class).get(0);
            Operation operation = new Operation(account);

            operationActivity.editOperationWithForm(operation);
            persistor.create(operation);
            operationActivity.onSuccess(TYPE_CREATE);
        }
    }

    private static String[] validFormInputs(OperationForm operationForm) {
        //TODO

        return new String[]{};
    }

    public static void updateOperation(OperationActivity operationActivity, Operation operation, OperationForm operationForm) {
        String[] formErrors = validFormInputs(operationForm);

        if (formErrors.length != 0) {
            operationActivity.onError(formErrors[0]);
        } else {
            operationActivity.editOperationWithForm(operation);
            persistor.update(operation);
            operationActivity.onSuccess(TYPE_UPDATE);
        }
    }

    public static void deleteOperation(OperationActivity operationActivity, Operation operation) {
        persistor.delete(operation);
        operationActivity.onSuccess(TYPE_DELETE);
    }
}

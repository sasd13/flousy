package com.sasd13.flousy.content.handler;

import com.sasd13.flousy.OperationActivity;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.FormException;
import com.sasd13.flousy.content.form.OperationForm;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.EnumParameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class OperationHandler {

    private OperationActivity operationActivity;
    private LayeredPersistor persistor;
    private Map<String, String[]> parameters;

    public OperationHandler(OperationActivity operationActivity) {
        this.operationActivity = operationActivity;
        persistor = new LayeredPersistor(SQLiteDAO.create(operationActivity));
        parameters = new HashMap<>();
    }

    public Operation readOperation() {
        long id = operationActivity.getIntent().getLongExtra(Extra.OPERATION_ID, 0);

        return persistor.read(id, Operation.class);
    }

    public Operation getDefaultValueOfOperation() {
        Operation operation = new Operation();
        operation.setDateRealization(new Timestamp(System.currentTimeMillis()));

        return operation;
    }

    public void createOperation(OperationForm operationForm) {
        parameters.clear();
        parameters.put(
                EnumParameter.CUSTOMER.getName(),
                new String[]{ String.valueOf(SessionHelper.getExtraIdFromSession(operationActivity, Extra.CUSTOMER_ID))}
        );

        Account account = persistor.read(parameters, Account.class).get(0);
        Operation operation = new Operation(account);

        try {
            editOperationWithForm(operation, operationForm);
            persistor.create(operation);
            operationActivity.onCreateSucceeded();
        } catch (FormException e) {
            operationActivity.onError(e.getMessage());
        }
    }

    public void updateOperation(Operation operation, OperationForm operationForm) {
        try {
            editOperationWithForm(operation, operationForm);
            persistor.update(operation);
            operationActivity.onUpdateSucceeded();
        } catch (FormException e) {
            operationActivity.onError(e.getMessage());
        }
    }

    private void editOperationWithForm(Operation operation, OperationForm operationForm) throws FormException {
        Operation operationFromForm = operationForm.getEditable();

        operation.setDateRealization(operationFromForm.getDateRealization());
        operation.setTitle(operationFromForm.getTitle());
        operation.setAmount(operationFromForm.getAmount());
        operation.setType(operationFromForm.getType());
    }

    public void deleteOperation(Operation operation) {
        persistor.delete(operation);
        operationActivity.onDeleteSucceeded();
    }
}

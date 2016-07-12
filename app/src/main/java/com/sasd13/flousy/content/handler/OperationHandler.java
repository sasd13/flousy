package com.sasd13.flousy.content.handler;

import android.content.Intent;
import android.widget.Toast;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.ConsultActivity;
import com.sasd13.flousy.OperationActivity;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.FormException;
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

    private static final int TYPE_CREATE = 0;
    private static final int TYPE_UPDATE = 1;
    private static final int TYPE_DELETE = 2;

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

    public void createOperation(OperationForm operationForm) {
        parameters.clear();
        parameters.put(
                Parameter.CUSTOMER.getName(),
                new String[]{ String.valueOf(SessionHelper.getExtraIdFromSession(operationActivity, Extra.CUSTOMER_ID))}
        );

        Account account = persistor.read(parameters, Account.class).get(0);
        Operation operation = new Operation(account);

        try {
            editOperationWithForm(operation, operationForm);
            persistor.create(operation);
            onSuccess(TYPE_CREATE);
        } catch (FormException e) {
            onError(e.getMessage());
        }
    }

    private void onError(String error) {
        Toast.makeText(operationActivity, error, Toast.LENGTH_SHORT).show();
    }

    private void onSuccess(int type) {
        switch (type) {
            case OperationHandler.TYPE_CREATE:
                Toast.makeText(operationActivity, R.string.message_saved, Toast.LENGTH_SHORT).show();
                goToConsultActivity();
                break;
            case OperationHandler.TYPE_UPDATE:
                Toast.makeText(operationActivity, R.string.message_saved, Toast.LENGTH_SHORT).show();
                break;
            case OperationHandler.TYPE_DELETE:
                Toast.makeText(operationActivity, R.string.message_deleted, Toast.LENGTH_SHORT).show();
                goToConsultActivity();
                break;
        }
    }

    private void goToConsultActivity() {
        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                operationActivity.startActivity(new Intent(operationActivity, ConsultActivity.class));
                operationActivity.finish();
            }
        }, GUIConstants.TIMEOUT_ACTIVITY).start();
    }

    public void updateOperation(Operation operation, OperationForm operationForm) {
        try {
            editOperationWithForm(operation, operationForm);
            persistor.update(operation);
            onSuccess(TYPE_UPDATE);
        } catch (FormException e) {
            onError(e.getMessage());
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
        onSuccess(TYPE_DELETE);
    }
}

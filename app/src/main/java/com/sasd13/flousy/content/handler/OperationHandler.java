package com.sasd13.flousy.content.handler;

import android.content.Intent;
import android.widget.Toast;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.ConsultActivity;
import com.sasd13.flousy.OperationActivity;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.OperationForm;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.sql.Timestamp;
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
    private OperationForm operationForm;
    private LayeredPersistor persistor;
    private Map<String, String[]> parameters;
    private Operation operation;

    public OperationHandler(OperationActivity operationActivity, OperationForm operationForm) {
        this.operationActivity = operationActivity;
        this.operationForm = operationForm;
        persistor = new LayeredPersistor(SQLiteDAO.create(operationActivity));
        parameters = new HashMap<>();
    }

    public Operation readOperation() {
        long id = operationActivity.getIntent().getLongExtra(Extra.OPERATION_ID, 0);
        operation = persistor.read(id, Operation.class);

        return operation;
    }

    public void createOperation() {
        String[] errors = validFormInputs();

        if (errors.length != 0) {
            onError(errors[0]);
        } else {
            parameters.clear();
            parameters.put(
                    Parameter.CUSTOMER.getName(),
                    new String[]{ String.valueOf(SessionHelper.getExtraIdFromSession(operationActivity, Extra.CUSTOMER_ID))}
            );

            Account account = persistor.read(parameters, Account.class).get(0);
            Operation operation = new Operation(account);

            editOperationWithForm(operation);
            persistor.create(operation);
            onSuccess(TYPE_CREATE);
        }
    }

    private String[] validFormInputs() {
        //TODO

        return new String[]{};
    }

    private void onError(String error) {
        OptionDialog.showOkDialog(operationActivity, operationActivity.getResources().getString(R.string.title_error), error);
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

    public void updateOperation() {
        String[] formErrors = validFormInputs();

        if (formErrors.length != 0) {
            onError(formErrors[0]);
        } else {
            editOperationWithForm(operation);

            persistor.update(operation);
            onSuccess(TYPE_UPDATE);
        }
    }

    private void editOperationWithForm(Operation operation) {
        operation.setDateRealization(new Timestamp(operationForm.getDateRealization().toDate().getTime()));
        operation.setTitle(operationForm.getTitle());

        String amount = operationForm.getAmount();

        switch (operationForm.getType()) {
            case 0:
                operation.setAmount(0 - Math.abs(Double.parseDouble(amount)));
                break;
            case 1:
                operation.setAmount(Math.abs(Double.parseDouble(amount)));
                break;
        }
    }

    public void deleteOperation() {
        persistor.delete(operation);
        onSuccess(TYPE_DELETE);
    }
}

package com.sasd13.flousy.handler;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.util.builder.consult.DefaultOperationBuilder;
import com.sasd13.flousy.db.SQLiteDAO;
import com.sasd13.flousy.gui.form.OperationForm;
import com.sasd13.flousy.fragment.operation.OperationFragment;
import com.sasd13.flousy.util.Binder;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class OperationHandler {

    private OperationFragment operationFragment;
    private DefaultOperationBuilder defaultOperationBuilder;
    private LayeredPersistor persistor;

    public OperationHandler(OperationFragment operationFragment) {
        this.operationFragment = operationFragment;
        defaultOperationBuilder = new DefaultOperationBuilder();
        persistor = new LayeredPersistor(SQLiteDAO.create(operationFragment.getContext()));
    }

    public Operation getDefaultValueOfOperation() {
        return defaultOperationBuilder.build();
    }

    public void createOperation(OperationForm operationForm, Account account) {
        try {
            Operation operationFromForm = operationForm.getEditable();
            Operation operation = new Operation(account);

            Binder.bind(operation, operationFromForm);
            persistor.create(operation);
            operationFragment.onCreateSucceeded(operation);
        } catch (FormException e) {
            operationFragment.onError(e.getResMessage());
        }
    }

    public void updateOperation(Operation operation, OperationForm operationForm) {
        try {
            Operation operationFromForm = operationForm.getEditable();

            Binder.bind(operation, operationFromForm);
            persistor.update(operation);
            operationFragment.onUpdateSucceeded();
        } catch (FormException e) {
            operationFragment.onError(e.getResMessage());
        }
    }

    public void deleteOperation(Operation operation) {
        operation.getAccount().removeOperation(operation);
        persistor.delete(operation);
        operationFragment.onDeleteSucceeded();
    }
}

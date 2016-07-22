package com.sasd13.flousy.handler.consult;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.gui.form.FormException;
import com.sasd13.flousy.gui.form.OperationForm;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.fragment.consult.OperationFragment;
import com.sasd13.javaex.db.LayeredPersistor;

import java.sql.Timestamp;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class OperationHandler {

    private OperationFragment operationFragment;
    private LayeredPersistor persistor;

    public OperationHandler(OperationFragment operationFragment) {
        this.operationFragment = operationFragment;
        persistor = new LayeredPersistor(SQLiteDAO.create(operationFragment.getContext()));
    }

    public Operation getDefaultValueOfOperation() {
        Operation operation = new Operation();
        operation.setDateRealization(new Timestamp(System.currentTimeMillis()));

        return operation;
    }

    public void createOperation(OperationForm operationForm, Account account) {
        Operation operation = new Operation(account);

        try {
            editOperationWithForm(operation, operationForm);
            persistor.create(operation);
            operationFragment.onCreateSucceeded(operation);
        } catch (FormException e) {
            account.removeOperation(operation);
            operationFragment.onError(e.getMessage());
        }
    }

    public void updateOperation(Operation operation, OperationForm operationForm) {
        try {
            editOperationWithForm(operation, operationForm);
            persistor.update(operation);
            operationFragment.onUpdateSucceeded();
        } catch (FormException e) {
            operationFragment.onError(e.getMessage());
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
        operation.getAccount().removeOperation(operation);
        persistor.delete(operation);
        operationFragment.onDeleteSucceeded();
    }
}

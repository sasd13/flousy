package com.sasd13.flousy.content.handler;

import android.content.Context;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.OperationForm;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class OperationHandler {

    private static OperationForm operationForm;
    private static LayeredPersistor persistor;

    public static void init(Context context) {
        operationForm = new OperationForm(context);
        persistor = new LayeredPersistor(SQLiteDAO.getInstance());
    }

    public static OperationForm getOperationForm() {
        return operationForm;
    }

    public static void fillEditFormOperation(Operation operation) {
        operationForm.setDateRealization(new LocalDate(operation.getDateRealization()));
        operationForm.setTitle(operation.getTitle());
        operationForm.setAmount(String.valueOf(Math.abs(operation.getAmount())));

        String[] items = {"Débit", "Crédit"};

        if (operation.getAmount() <= 0) {
            operationForm.setType(0, items);
        } else {
            operationForm.setType(1, items);
        }
    }

    public static Operation readOperation(long id) {
        return persistor.read(id, Operation.class);
    }

    public static void fillNewFormOperation() {
        operationForm.setDateRealization(new LocalDate(System.currentTimeMillis()));
        operationForm.setType(0, new String[] {"Débit", "Crédit"});
    }

    public static String[] validFormInputs() {
        //TODO

        return new String[]{};
    }

    public static void createOperation() {
        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.CUSTOMER.getName(), new String[]{ String.valueOf(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID))});

        Account account = persistor.read(parameters, Account.class).get(0);
        Operation operation = new Operation(account);

        editOperationWithForm(operation);
        persistor.create(operation);
    }

    private static void editOperationWithForm(Operation operation) {
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

    public static void updateOperation(Operation operation) {
        editOperationWithForm(operation);
        persistor.update(operation);
    }

    public static void deleteOperation(Operation operation) {
        persistor.delete(operation);
    }
}

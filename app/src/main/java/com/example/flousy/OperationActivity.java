package com.example.flousy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.sql.Timestamp;

import flousy.beans.core.Account;
import flousy.beans.core.Operation;
import flousy.beans.core.OperationType;
import flousy.constant.Extra;
import flousy.db.DataAccessor;
import flousy.db.DataAccessorFactory;
import flousy.form.FormValidator;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.session.Session;

public class OperationActivity extends MotherActivity {

    private class FormOperationViewHolder {
        public TextView textViewDate;
        public EditText editTextName, editTextValue;
        public RadioGroup radioGroupType;
        public RadioButton radioButtonDebit, radioButtonCredit;
    }

    private FormOperationViewHolder formOperation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_operation);

        createFormOperation();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getExtraMode() == Extra.MODE_NEW) {
            fillNewFormOperation();
        } else {
            Operation operation = DataAccessorFactory.get().selectOperation(getOperationIdFromIntent());

            fillEditFormOperation(operation);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_operation, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (getExtraMode() == Extra.MODE_NEW) {
            menu.findItem(R.id.menu_operation_action_discard).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_operation_action_accept:
                if (getExtraMode() == Extra.MODE_NEW) {
                    createOperation();
                } else {
                    updateOperation();
                }
                break;
            case R.id.menu_operation_action_discard:
                deleteOperation();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createFormOperation() {
        this.formOperation = new FormOperationViewHolder();

        this.formOperation.textViewDate = (TextView) findViewById(R.id.form_operation_textview_date);
        this.formOperation.editTextName = (EditText) findViewById(R.id.form_operation_edittext_name);
        this.formOperation.editTextValue = (EditText) findViewById(R.id.form_operation_edittext_value);

        this.formOperation.radioGroupType = (RadioGroup) findViewById(R.id.form_operation_radiogroup_type);
        this.formOperation.radioButtonDebit = (RadioButton) findViewById(R.id.form_operation_radiobutton_type_debit);
        this.formOperation.radioButtonCredit = (RadioButton) findViewById(R.id.form_operation_radiobutton_type_credit);
    }

    private int getExtraMode() {
        return getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW);
    }

    private long getOperationIdFromIntent() {
        return getIntent().getLongExtra(Extra.OPERATION_ID, Extra.NULL_ID);
    }

    private void fillEditFormOperation(Operation operation) {
        this.formOperation.textViewDate.setText(String.valueOf(operation.getDate()));
        this.formOperation.editTextName.setText(operation.getName(), TextView.BufferType.EDITABLE);
        this.formOperation.editTextValue.setText(String.valueOf(Math.abs(operation.getValue())), TextView.BufferType.EDITABLE);

        switch (operation.getType()) {
            case DEBIT:
                this.formOperation.radioButtonDebit.setChecked(true);
                break;
            case CREDIT:
                this.formOperation.radioButtonCredit.setChecked(true);
                break;
        }
    }

    private void fillNewFormOperation() {
        this.formOperation.textViewDate.setText(String.valueOf(new Timestamp(System.currentTimeMillis())));
        this.formOperation.radioButtonDebit.setChecked(true);
    }

    private void createOperation() {
        String[] tabFormErrors = validFormOperation();

        if (tabFormErrors.length == 0) {
            Operation operation = getOperationFromForm();

            DataAccessor dao = DataAccessorFactory.get();

            Account account = dao.selectAccountByUserWithOperations(Session.getUserId());

            dao.insertOperation(operation, account);

            account.getListOperations().add(operation);
            dao.updateAccount(account);

            goToAccountActivity();
        } else {
            CustomDialog.showOkDialog(this, "Form error", tabFormErrors[0]);
        }
    }

    private String[] validFormOperation() {
        FormValidator formValidator = new FormValidator();

        String name = this.formOperation.editTextName.getText().toString().trim();
        //String value = this.formOperation.editTextValue.getText().toString().trim();

        formValidator.validName(name, "name");
        //formValidator.validNumber(value, "value");

        return formValidator.getErrors();
    }

    private Operation getOperationFromForm() {
        Operation operation = new Operation();

        switch (this.formOperation.radioGroupType.getCheckedRadioButtonId()) {
            case R.id.form_operation_radiobutton_type_debit:
                operation.setType(OperationType.DEBIT);
                break;
            case R.id.form_operation_radiobutton_type_credit:
                operation.setType(OperationType.CREDIT);
                break;
        }

        String name = this.formOperation.editTextName.getText().toString().trim();
        String value = this.formOperation.editTextValue.getText().toString().trim();

        operation.setName(name);
        operation.setValue(Double.parseDouble(value));

        return operation;
    }

    private void goToAccountActivity() {
        Intent intent = new Intent(this, AccountActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    private void updateOperation() {
        String[] tabFormErrors = validFormOperation();

        if (tabFormErrors.length == 0) {
            DataAccessor dao = DataAccessorFactory.get();

            Account account = dao.selectAccountByUserWithOperations(Session.getUserId());

            Operation operation = account.getListOperations().get(getOperationIdFromIntent());
            editOperationWithForm(operation);
            dao.updateOperation(operation);

            account.update();
            dao.updateAccount(account);

            goToAccountActivity();
        } else {
            CustomDialog.showOkDialog(this, "Form error", tabFormErrors[0]);
        }
    }

    private void editOperationWithForm(Operation operation) {
        Operation operationFromForm = getOperationFromForm();

        operation.setType(operationFromForm.getType());
        operation.setName(operationFromForm.getName());
        operation.setValue(operationFromForm.getValue());
    }

    private void deleteOperation() {
        CustomDialog.showYesNoDialog(
                this,
                "Operation",
                "Confirm ?",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmDeleteOperation();
                    }
                }
        );
    }

    private void confirmDeleteOperation() {
        DataAccessor dao = DataAccessorFactory.get();

        Account account = dao.selectAccountByUserWithOperations(Session.getUserId());

        Operation operation = account.getListOperations().get(getOperationIdFromIntent());
        dao.deleteOperation(operation);

        account.getListOperations().remove(operation);
        dao.updateAccount(account);

        CustomDialog.showOkDialog(this, "Operation", "Operation deleted");

        goToAccountActivity();
    }
}
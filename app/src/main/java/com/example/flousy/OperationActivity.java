package com.example.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import flousy.beans.core.Account;
import flousy.beans.core.Operation;
import flousy.beans.core.OperationType;
import flousy.constant.Extra;
import flousy.db.DBManager;
import flousy.db.DataAccessor;
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

    private DataAccessor dao;
    private long operationId;
    private FormOperationViewHolder formOperation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview);

        createFormOperation();
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.dao = DBManager.getDao();

        if (getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW) == Extra.MODE_EDIT) {
            this.operationId = getIntent().getLongExtra(Extra.OPERATION_ID, Extra.NULL_ID);

            fillFormOperation();
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

        if (getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW) == Extra.MODE_NEW) {
            menu.findItem(R.id.menu_operation_action_edit).setVisible(false);
            menu.findItem(R.id.menu_operation_action_discard).setVisible(false);
        } else {
            menu.findItem(R.id.menu_operation_action_accept).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_operation_action_accept:
                createOperation();
                break;
            case R.id.menu_operation_action_edit:
                updateOperation();
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

        this.formOperation.textViewDate = (TextView) findViewById(0);
        this.formOperation.editTextName = (EditText) findViewById(0);
        this.formOperation.editTextValue = (EditText) findViewById(0);

        this.formOperation.radioGroupType = (RadioGroup) findViewById(0);
        this.formOperation.radioButtonDebit = (RadioButton) findViewById(0);
        this.formOperation.radioButtonCredit = (RadioButton) findViewById(0);
    }

    private void fillFormOperation() {
        Operation operation = this.dao.selectOperation(this.operationId);

        this.formOperation.textViewDate.setText(String.valueOf(operation.getDate()));
        this.formOperation.editTextName.setText(operation.getName(), TextView.BufferType.EDITABLE);
        this.formOperation.editTextValue.setText(String.valueOf(operation.getValue()), TextView.BufferType.EDITABLE);

        switch (operation.getType()) {
            case CREDIT:
                this.formOperation.radioButtonCredit.setChecked(true);
                break;
            case DEBIT: default:
                this.formOperation.radioButtonDebit.setChecked(true);
                break;
        }
    }

    private void createOperation() {
        String[] tabFormErrors = validFormOperation();

        if (tabFormErrors.length == 0) {
            Operation operation = getOperationFromForm();
            Account account = this.dao.selectAccount(Long.parseLong(Session.getAccountId()));

            this.dao.insertOperation(operation, account);

            goToAccountActivity();
        } else {
            CustomDialog.showOkDialog(this, "Form error", tabFormErrors[0]);
        }
    }

    private String[] validFormOperation() {
        FormValidator formValidator = new FormValidator();

        String name = this.formOperation.editTextName.getText().toString().trim();
        String value = this.formOperation.editTextValue.getText().toString().trim();

        formValidator.validName(name, "name");
        formValidator.validNumber(value, "value");

        return formValidator.getErrors();
    }

    private Operation getOperationFromForm() {
        Operation operation = new Operation();

        String name = this.formOperation.editTextName.getText().toString().trim();
        String value = this.formOperation.editTextValue.getText().toString().trim();

        operation.setName(name);
        operation.setValue(Double.valueOf(value));

        switch (this.formOperation.radioGroupType.getCheckedRadioButtonId()) {
            case 0:
                operation.setType(OperationType.CREDIT);
                break;
            case 1: default:
                operation.setType(OperationType.DEBIT);
                break;
        }

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
            Operation operation = editOperationWithForm();

            this.dao.updateOperation(operation);

            goToAccountActivity();
        } else {
            CustomDialog.showOkDialog(this, "Form error", tabFormErrors[0]);
        }
    }

    private Operation editOperationWithForm() {
        Operation operation = this.dao.selectOperation(this.operationId);

        Operation operationFromForm = getOperationFromForm();
        operation.setName(operationFromForm.getName());
        operation.setValue(operationFromForm.getValue());
        operation.setType(operationFromForm.getType());

        return operation;
    }

    private void deleteOperation() {
        Operation operation = this.dao.selectOperation(this.operationId);

        this.dao.deleteOperation(operation);

        goToAccountActivity();
    }
}
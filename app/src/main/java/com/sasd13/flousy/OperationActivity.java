package com.sasd13.flousy;

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
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class OperationActivity extends MotherActivity {

    private class FormOperationViewHolder {
        public TextView textViewDateRealization;
        public EditText editTextTitle, editTextAmount;
        public RadioGroup radioGroupType;
        public RadioButton radioButtonDebit, radioButtonCredit;
    }

    private FormOperationViewHolder formOperation;

    private Operation operation;
    private LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.getInstance());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_operation);
        GUIHelper.colorTitles(this);

        createFormOperation();
    }

    private void createFormOperation() {
        formOperation = new FormOperationViewHolder();

        //TODO
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (hasExtraModeNew()) {
            fillNewFormOperation();
        } else {
            operation = persistor.read(getOperationIdFromIntent(), Operation.class);

            fillEditFormOperation();
        }
    }

    private boolean hasExtraModeNew() {
        return getExtraMode() == Extra.MODE_NEW;
    }

    private int getExtraMode() {
        return getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW);
    }

    private void fillNewFormOperation() {
        formOperation.textViewDateRealization.setText(String.valueOf(new Timestamp(System.currentTimeMillis())));
        formOperation.radioButtonDebit.setChecked(true);
    }

    private long getOperationIdFromIntent() {
        return getIntent().getLongExtra(Extra.OPERATION_ID, 0);
    }

    private void fillEditFormOperation() {
        formOperation.textViewDateRealization.setText(String.valueOf(operation.getDateRealization()));
        formOperation.editTextTitle.setText(operation.getTitle(), TextView.BufferType.EDITABLE);
        formOperation.editTextAmount.setText(String.valueOf(Math.abs(operation.getAmount())), TextView.BufferType.EDITABLE);

        if (operation.getAmount() <= 0) {
            formOperation.radioButtonDebit.setChecked(true);
        } else {
            formOperation.radioButtonCredit.setChecked(true);
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

        if (hasExtraModeNew()) {
            menu.findItem(R.id.menu_operation_action_delete).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_operation_action_accept:
                createOrUpdateOperation();
                break;
            case R.id.menu_operation_action_delete:
                deleteOperation();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createOrUpdateOperation() {
        if (hasExtraModeNew()) {
            createOperation();
        } else {
            updateOperation();
        }
    }

    private void createOperation() {
        String[] tabFormErrors = validFormOperation();

        if (true) {
            performCreateOperation();
            Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
            goToAccountActivity();
        } else {
            OptionDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormOperation() {
        //TODO

        return null;
    }

    private void performCreateOperation() {
        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.CUSTOMER.getName(), new String[]{ String.valueOf(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID))});

        Account account = persistor.read(parameters, Account.class).get(0);

        Operation operation = new Operation(account);
        editOperationWithForm(operation);
        persistor.create(operation);
    }

    private void editOperationWithForm(Operation operation) {
        operation.setTitle(formOperation.editTextTitle.getText().toString().trim());

        String amount = formOperation.editTextAmount.getText().toString().trim();
        /*
        switch (formOperation.radioGroupType.getCheckedRadioButtonId()) {
            case R.id.form_operation_radiobutton_type_debit:
                operation.setAmount(0 - Math.abs(Double.parseDouble(amount)));
                break;
            case R.id.form_operation_radiobutton_type_credit:
                operation.setAmount(Math.abs(Double.parseDouble(amount)));
                break;
        }*/
    }

    private void goToAccountActivity() {
        startActivity(new Intent(this, AccountActivity.class));
        finish();
    }

    private void updateOperation() {
        String[] tabFormErrors = validFormOperation();

        if (true) {
            performUpdateOperation();
            Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
            goToAccountActivity();
        } else {
            OptionDialog.showOkDialog(this, getResources().getString(R.string.title_error), tabFormErrors[0]);
        }
    }

    private void performUpdateOperation() {
        editOperationWithForm(operation);
        persistor.update(operation);
    }

    private void deleteOperation() {
        OptionDialog.showYesNoDialog(
                this,
                getResources().getString(R.string.activity_operation),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        persistor.delete(operation);
                        Toast.makeText(OperationActivity.this, R.string.message_deleted, Toast.LENGTH_SHORT).show();
                        goToAccountActivity();
                    }
                }
        );
    }
}
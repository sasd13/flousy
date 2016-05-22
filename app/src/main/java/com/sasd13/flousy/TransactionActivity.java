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

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Transaction;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class TransactionActivity extends MotherActivity {

    private class FormTransactionViewHolder {
        public TextView textViewDateRealization;
        public EditText editTextTitle, editTextValue;
        public RadioGroup radioGroupType;
        public RadioButton radioButtonDebit, radioButtonCredit;
    }

    private FormTransactionViewHolder formTransaction;

    private Transaction transaction;
    private LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.getInstance());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transaction);
        createFormTransaction();
    }

    private void createFormTransaction() {
        formTransaction = new FormTransactionViewHolder();

        formTransaction.textViewDateRealization = (TextView) findViewById(R.id.form_transaction_textview_daterealization);
        formTransaction.editTextTitle = (EditText) findViewById(R.id.form_transaction_edittext_title);
        formTransaction.editTextValue = (EditText) findViewById(R.id.form_transaction_edittext_value);
        formTransaction.radioGroupType = (RadioGroup) findViewById(R.id.form_transaction_radiogroup_type);
        formTransaction.radioButtonDebit = (RadioButton) findViewById(R.id.form_transaction_radiobutton_type_debit);
        formTransaction.radioButtonCredit = (RadioButton) findViewById(R.id.form_transaction_radiobutton_type_credit);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (hasExtraModeNew()) {
            fillNewFormTransaction();
        } else {
            transaction = persistor.read(getTransactionIdFromIntent(), Transaction.class);

            fillEditFormTransaction();
        }
    }

    private boolean hasExtraModeNew() {
        return getExtraMode() == Extra.MODE_NEW;
    }

    private int getExtraMode() {
        return getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW);
    }

    private void fillNewFormTransaction() {
        formTransaction.textViewDateRealization.setText(String.valueOf(new Timestamp(System.currentTimeMillis())));
        formTransaction.radioButtonDebit.setChecked(true);
    }

    private long getTransactionIdFromIntent() {
        return getIntent().getLongExtra(Extra.TRANSACTION_ID, 0);
    }

    private void fillEditFormTransaction() {
        formTransaction.textViewDateRealization.setText(String.valueOf(transaction.getDateRealization()));
        formTransaction.editTextTitle.setText(transaction.getTitle(), TextView.BufferType.EDITABLE);
        formTransaction.editTextValue.setText(String.valueOf(Math.abs(transaction.getAmount())), TextView.BufferType.EDITABLE);

        if (transaction.getAmount() <= 0) {
            formTransaction.radioButtonDebit.setChecked(true);
        } else {
            formTransaction.radioButtonCredit.setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_transaction, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (hasExtraModeNew()) {
            menu.findItem(R.id.menu_transaction_action_discard).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_transaction_action_accept:
                createOrUpdateTransaction();
                break;
            case R.id.menu_transaction_action_discard:
                deleteTransaction();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createOrUpdateTransaction() {
        if (hasExtraModeNew()) {
            createTransaction();
        } else {
            updateTransaction();
        }
    }

    private void createTransaction() {
        String[] tabFormErrors = validFormTransaction();

        if (true) {
            performCreateTransaction();
            Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
            goToAccountActivity();
        } else {
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormTransaction() {
        //TODO

        return null;
    }

    private void performCreateTransaction() {
        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.CUSTOMER.getName(), new String[]{ String.valueOf(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID))});

        Account account = persistor.read(parameters, Account.class).get(0);

        Transaction transaction = new Transaction(account);
        editTransactionWithForm(transaction);
        persistor.create(transaction);
    }

    private void editTransactionWithForm(Transaction transaction) {
        transaction.setTitle(formTransaction.editTextTitle.getText().toString().trim());

        String value = formTransaction.editTextValue.getText().toString().trim();
        switch (formTransaction.radioGroupType.getCheckedRadioButtonId()) {
            case R.id.form_transaction_radiobutton_type_debit:
                transaction.setAmount(0 - Math.abs(Double.parseDouble(value)));
                break;
            case R.id.form_transaction_radiobutton_type_credit:
                transaction.setAmount(Math.abs(Double.parseDouble(value)));
                break;
        }
    }

    private void goToAccountActivity() {
        Intent intent = new Intent(this, AccountActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    private void updateTransaction() {
        String[] tabFormErrors = validFormTransaction();

        if (true) {
            performUpdateTransaction();
            Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
            goToAccountActivity();
        } else {
            CustomDialog.showOkDialog(this, getResources().getString(R.string.title_error), tabFormErrors[0]);
        }
    }

    private void performUpdateTransaction() {
        editTransactionWithForm(transaction);
        persistor.update(transaction);
    }

    private void deleteTransaction() {
        CustomDialog.showYesNoDialog(
                this,
                getResources().getString(R.string.activity_transaction),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        persistor.delete(transaction);
                        Toast.makeText(TransactionActivity.this, R.string.message_deleted, Toast.LENGTH_SHORT).show();
                        goToAccountActivity();
                    }
                }
        );
    }
}
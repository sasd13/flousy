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

import flousy.beans.Account;
import flousy.beans.Transaction;
import flousy.constant.Extra;
import flousy.db.DataAccessor;
import flousy.db.DataAccessorFactory;
import flousy.form.FormValidator;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.session.Session;

public class TransactionActivity extends MotherActivity {

    private class FormTransactionViewHolder {
        public TextView textViewDateRealization;
        public EditText editTextTitle, editTextValue;
        public RadioGroup radioGroupType;
        public RadioButton radioButtonDebit, radioButtonCredit;
    }

    private FormTransactionViewHolder formTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transaction);

        createFormTransaction();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getExtraMode() == Extra.MODE_NEW) {
            fillNewFormTransaction();
        } else {
            Transaction transaction = DataAccessorFactory.get().selectTransaction(getTransactionIdFromIntent());

            fillEditFormTransaction(transaction);
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

        if (getExtraMode() == Extra.MODE_NEW) {
            menu.findItem(R.id.menu_transaction_action_discard).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_transaction_action_accept:
                if (getExtraMode() == Extra.MODE_NEW) {
                    createTransaction();
                } else {
                    updateTransaction();
                }
                break;
            case R.id.menu_transaction_action_discard:
                deleteTransaction();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createFormTransaction() {
        this.formTransaction = new FormTransactionViewHolder();

        this.formTransaction.textViewDateRealization = (TextView) findViewById(R.id.form_transaction_textview_daterealization);
        this.formTransaction.editTextTitle = (EditText) findViewById(R.id.form_transaction_edittext_title);
        this.formTransaction.editTextValue = (EditText) findViewById(R.id.form_transaction_edittext_value);

        this.formTransaction.radioGroupType = (RadioGroup) findViewById(R.id.form_transaction_radiogroup_type);
        this.formTransaction.radioButtonDebit = (RadioButton) findViewById(R.id.form_transaction_radiobutton_type_debit);
        this.formTransaction.radioButtonCredit = (RadioButton) findViewById(R.id.form_transaction_radiobutton_type_credit);
    }

    private int getExtraMode() {
        return getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW);
    }

    private long getTransactionIdFromIntent() {
        return getIntent().getLongExtra(Extra.TRANSACTION_ID, 0);
    }

    private void fillEditFormTransaction(Transaction transaction) {
        this.formTransaction.textViewDateRealization.setText(String.valueOf(transaction.getDateRealization()));
        this.formTransaction.editTextTitle.setText(transaction.getTitle(), TextView.BufferType.EDITABLE);
        this.formTransaction.editTextValue.setText(String.valueOf(Math.abs(transaction.getValue())), TextView.BufferType.EDITABLE);

        if (transaction.getValue() <= 0) {
            this.formTransaction.radioButtonDebit.setChecked(true);
        } else {
            this.formTransaction.radioButtonCredit.setChecked(true);
        }
    }

    private void fillNewFormTransaction() {
        this.formTransaction.textViewDateRealization.setText(String.valueOf(new Timestamp(System.currentTimeMillis())));
        this.formTransaction.radioButtonDebit.setChecked(true);
    }

    private void createTransaction() {
        String[] tabFormErrors = validFormTransaction();

        if (tabFormErrors.length == 0) {
            Transaction transaction = getTransactionFromForm();

            DataAccessor dao = DataAccessorFactory.get();

            Account account = dao.selectAccount(Session.getAccountId());
            transaction.setAccount(account);

            dao.insertTransaction(transaction);

            goToAccountActivity();
        } else {
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormTransaction() {
        FormValidator formValidator = new FormValidator();

        String title = this.formTransaction.editTextTitle.getText().toString().trim();
        //String value = this.formTransaction.editTextValue.getText().toString().trim();

        formValidator.validName(title, "title");
        //formValidator.validNumber(value, "value");

        return formValidator.getErrors();
    }

    private Transaction getTransactionFromForm() {
        Transaction transaction = new Transaction();

        String title = this.formTransaction.editTextTitle.getText().toString().trim();
        String value = this.formTransaction.editTextValue.getText().toString().trim();

        transaction.setTitle(title);
        switch (this.formTransaction.radioGroupType.getCheckedRadioButtonId()) {
            case R.id.form_transaction_radiobutton_type_debit:
                transaction.setValue(0 - Math.abs(Double.parseDouble(value)));
                break;
            case R.id.form_transaction_radiobutton_type_credit:
                transaction.setValue(Math.abs(Double.parseDouble(value)));
                break;
        }

        return transaction;
    }

    private void goToAccountActivity() {
        Intent intent = new Intent(this, AccountActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    private void updateTransaction() {
        String[] tabFormErrors = validFormTransaction();

        if (tabFormErrors.length == 0) {
            DataAccessor dao = DataAccessorFactory.get();

            Transaction transaction = dao.selectTransaction(getTransactionIdFromIntent());

            editTransactionWithForm(transaction);
            dao.updateTransaction(transaction);

            goToAccountActivity();
        } else {
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private void editTransactionWithForm(Transaction transaction) {
        Transaction transactionFromForm = getTransactionFromForm();

        transaction.setTitle(transactionFromForm.getTitle());
        transaction.setValue(transactionFromForm.getValue());
    }

    private void deleteTransaction() {
        CustomDialog.showYesNoDialog(
                this,
                "Transaction",
                "Confirm ?",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmDeleteTransaction();
                    }
                }
        );
    }

    private void confirmDeleteTransaction() {
        DataAccessorFactory.get().deleteTransaction(getTransactionIdFromIntent());

        CustomDialog.showOkDialog(this, "Transaction", "Transaction deleted");

        goToAccountActivity();
    }
}
package com.sasd13.flousy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.form.Form;
import com.sasd13.androidex.gui.widget.recycler.form.FormFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.OperationForm;
import com.sasd13.flousy.content.handler.OperationHandler;

import org.joda.time.LocalDate;

import java.sql.Timestamp;

public class OperationActivity extends MotherActivity {

    private OperationForm operationForm;
    private Operation operation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_operation);
        GUIHelper.colorTitles(this);
        OperationHandler.init(this);
        buildOperationView();
    }

    private void buildOperationView() {
        operationForm = new OperationForm(this);
        FormFactory formFactory = new FormFactory(this);
        Form form = (Form) formFactory.makeBuilder().build((RecyclerView) findViewById(R.id.operation_recyclerview));

        RecyclerHelper.fill(form, operationForm.fabricate(), formFactory);
        fillFormWithOperation();
    }

    private void fillFormWithOperation() {
        if (hasExtraModeEdit()) {
            operation = OperationHandler.readOperation(getIntent().getLongExtra(Extra.OPERATION_ID, 0));

            fillEditFormOperation();
            getSupportActionBar().setSubtitle(getResources().getString(R.string.subtitle_consulting));
        } else {
            fillNewFormOperation();
            getSupportActionBar().setSubtitle(getResources().getString(R.string.subtitle_new));
        }
    }

    private boolean hasExtraModeEdit() {
        return getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW) == Extra.MODE_EDIT;
    }

    private void fillEditFormOperation() {
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

    private void fillNewFormOperation() {
        operationForm.setDateRealization(new LocalDate(System.currentTimeMillis()));
        operationForm.setType(0, new String[] {"Débit", "Crédit"});
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

        if (!hasExtraModeEdit()) {
            menu.findItem(R.id.menu_operation_action_delete).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_operation_action_accept:
                saveOperation();
                break;
            case R.id.menu_operation_action_delete:
                deleteOperation();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void saveOperation() {
        if (hasExtraModeEdit()) {
            OperationHandler.updateOperation(operation, operationForm);
        } else {
            OperationHandler.createOperation(operationForm);
        }
    }

    private void deleteOperation() {
        OptionDialog.showYesNoDialog(
                this,
                getResources().getString(R.string.activity_operation),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OperationHandler.deleteOperation(operation);
                    }
                }
        );
    }

    public void editOperationWithForm(Operation operation) {
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

    public void onSuccess(int type) {
        switch (type) {
            case OperationHandler.TYPE_CREATE: case OperationHandler.TYPE_UPDATE:
                Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
                break;
            case OperationHandler.TYPE_DELETE:
                Toast.makeText(OperationActivity.this, R.string.message_deleted, Toast.LENGTH_SHORT).show();
                goToConsultActivity();
                break;
        }
    }

    public void onError(String error) {
        OptionDialog.showOkDialog(this, getResources().getString(R.string.title_error), error);
    }

    private void goToConsultActivity() {
        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(OperationActivity.this, ConsultActivity.class));
                finish();
            }
        }, GUIConstants.TIMEOUT_ACTIVITY).start();
    }
}
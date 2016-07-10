package com.sasd13.flousy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactoryType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.OperationForm;
import com.sasd13.flousy.content.handler.OperationHandler;

import org.joda.time.LocalDate;

import java.util.Arrays;

public class OperationActivity extends MotherActivity {

    private OperationForm operationForm;
    private OperationHandler operationHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        operationForm = new OperationForm(this);
        operationHandler = new OperationHandler(this, operationForm);

        setContentView(R.layout.activity_operation);
        GUIHelper.colorTitles(this);
        buildOperationView();
    }

    private void buildOperationView() {
        Recycler form = RecyclerHelper.produce(RecyclerFactoryType.FORM, (RecyclerView) findViewById(R.id.operation_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, operationForm.fabricate());
        fillFormWithOperation();
    }

    private void fillFormWithOperation() {
        if (hasExtraModeEdit()) {
            Operation operation = operationHandler.readOperation();

            fillEditFormOperation(operation);
            getSupportActionBar().setSubtitle(getResources().getString(R.string.subtitle_consulting));
        } else {
            fillNewFormOperation();
            getSupportActionBar().setSubtitle(getResources().getString(R.string.subtitle_new));
        }
    }

    private boolean hasExtraModeEdit() {
        return getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW) == Extra.MODE_EDIT;
    }

    private void fillEditFormOperation(Operation operation) {
        operationForm.setDateRealization(new LocalDate(operation.getDateRealization()));
        operationForm.setTitle(operation.getTitle());
        operationForm.setAmount(String.valueOf(Math.abs(operation.getAmount())));

        String[] operationTypes = getResources().getStringArray(R.array.operation_types);
        String debit = getResources().getString(R.string.operation_type_debit);
        String credit = getResources().getString(R.string.operation_type_credit);

        if (operation.getAmount() <= 0) {
            operationForm.setType(operationTypes, Arrays.asList(operationTypes).indexOf(debit));
        } else {
            operationForm.setType(operationTypes, Arrays.asList(operationTypes).indexOf(credit));
        }
    }

    private void fillNewFormOperation() {
        operationForm.setDateRealization(new LocalDate(System.currentTimeMillis()));
        operationForm.setType(getResources().getStringArray(R.array.operation_types));
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
            operationHandler.updateOperation();
        } else {
            operationHandler.createOperation();
        }
    }

    private void deleteOperation() {
        OptionDialog.showYesNoDialog(
                this,
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        operationHandler.deleteOperation();
                    }
                }
        );
    }
}
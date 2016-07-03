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
import com.sasd13.flousy.content.handler.OperationHandler;

public class OperationActivity extends MotherActivity {

    private Operation operation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OperationHandler.init(this);
        setContentView(R.layout.activity_operation);
        GUIHelper.colorTitles(this);
        buildOperationView();
    }

    private void buildOperationView() {
        FormFactory formFactory = new FormFactory(this);
        Form form = (Form) formFactory.makeBuilder().build((RecyclerView) findViewById(R.id.operation_recyclerview));

        RecyclerHelper.fill(form, OperationHandler.getOperationForm().fabricate(), formFactory);
        fillFormWithOperation();
    }

    private void fillFormWithOperation() {
        if (hasExtraModeEdit()) {
            operation = OperationHandler.readOperation(getIntent().getLongExtra(Extra.OPERATION_ID, 0));

            OperationHandler.fillEditFormOperation(operation);
        } else {
            getSupportActionBar().setSubtitle(getResources().getString(R.string.subtitle_new));
            OperationHandler.fillNewFormOperation();
        }
    }

    private boolean hasExtraModeEdit() {
        return getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW) == Extra.MODE_EDIT;
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
        String[] formErrors = OperationHandler.validFormInputs();

        if (formErrors.length == 0) {
            if (hasExtraModeEdit()) {
                OperationHandler.updateOperation(operation);
            } else {
                OperationHandler.createOperation();
            }

            Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
        } else {
            OptionDialog.showOkDialog(this, getResources().getString(R.string.title_error), formErrors[0]);
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
                        Toast.makeText(OperationActivity.this, R.string.message_deleted, Toast.LENGTH_SHORT).show();
                        goToConsultActivity();
                    }
                }
        );
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
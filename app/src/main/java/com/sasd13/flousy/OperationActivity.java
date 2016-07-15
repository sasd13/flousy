package com.sasd13.flousy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.FormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.OperationForm;
import com.sasd13.flousy.content.handler.OperationHandler;

import java.sql.Timestamp;

public class OperationActivity extends MotherActivity {

    private OperationHandler operationHandler;
    private OperationForm operationForm;
    private Operation operation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_operation);
        GUIHelper.colorTitles(this);

        operationHandler = new OperationHandler(this);
        operationForm = new OperationForm(this);

        buildOperationView();
    }

    private void buildOperationView() {
        Recycler form = RecyclerFactory.makeBuilder(FormType.FORM).build((RecyclerView) findViewById(R.id.operation_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, operationForm.getHolder());
        fillOperationView();
    }

    private void fillOperationView() {
        if (hasExtraModeEdit()) {
            operation = operationHandler.readOperation();

            getSupportActionBar().setSubtitle(getResources().getString(R.string.subtitle_consulting));
        } else {
            operation = new Operation();
            operation.setDateRealization(new Timestamp(System.currentTimeMillis()));

            getSupportActionBar().setSubtitle(getResources().getString(R.string.subtitle_new));
        }

        operationForm.bindOperation(operation);
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
        if (hasExtraModeEdit()) {
            operationHandler.updateOperation(operation, operationForm);
        } else {
            operationHandler.createOperation(operationForm);
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
                        operationHandler.deleteOperation(operation);
                    }
                }
        );
    }
}
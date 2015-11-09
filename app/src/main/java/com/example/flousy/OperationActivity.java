package com.example.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import flousy.beans.core.Operation;
import flousy.db.DBManager;
import flousy.db.DataAccessor;

public class OperationActivity extends MotherActivity {

    private class FormOperationViewHolder {

    }

    public static final int ACTIVITY_COLOR = R.color.customGreen;

    private DataAccessor dao = DBManager.getDao();

    private FormOperationViewHolder formOperation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview);

        setColor(getResources().getColor(ACTIVITY_COLOR));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createFormOperation() {
        this.formOperation = new FormOperationViewHolder();

        fillFormOperation();
    }

    private void fillFormOperation() {

    }

    private void createOperation() {

    }

    private Operation getOperationFromForm() {
        Operation operation = null;

        return operation;
    }

    private void updateOperation() {

    }

    private void deleteOperation() {

    }
}
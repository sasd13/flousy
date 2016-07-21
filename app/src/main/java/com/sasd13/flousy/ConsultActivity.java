package com.sasd13.flousy;

import android.os.Bundle;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.handler.consult.ConsultHandler;
import com.sasd13.flousy.util.SessionHelper;

public class ConsultActivity extends MotherActivity {

    private Account account;
    private ConsultHandler consultHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consult);
        GUIHelper.colorTitles(this);

        consultHandler = new ConsultHandler(this);

        buildView();
    }

    private void buildView() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        account = consultHandler.readAccount(SessionHelper.getExtraIdFromSession(this, Extra.CUSTOMER_ID));
    }

    public void listOperations() {

    }

    public void newOperation() {

    }

    public void notifyAddOperation(Operation operation) {
        account.getOperations().add(operation);
    }

    public void editOperation(Operation operation) {

    }

    public void notifyRemoveOperation(Operation operation) {
        account.getOperations().remove(operation);
    }
}
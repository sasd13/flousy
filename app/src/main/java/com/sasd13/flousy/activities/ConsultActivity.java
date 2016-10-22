package com.sasd13.flousy.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.activities.fragment.account.AccountFragment;
import com.sasd13.flousy.activities.fragment.operation.OperationFragment;
import com.sasd13.flousy.handler.ConsultHandler;
import com.sasd13.flousy.util.SessionHelper;

public class ConsultActivity extends MotherActivity {

    private Account account;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consult);
        GUIHelper.colorTitles(this);

        account = new ConsultHandler(this).readAccount(SessionHelper.getExtraId(this, Extra.CUSTOMER_ID));

        buildView();
    }

    private void buildView() {
        listOperations();
    }

    public void listOperations() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.consult_fragment, AccountFragment.newInstance(account))
                .commit();
    }

    public void newOperation() {
        startFragment(OperationFragment.newInstance(account));
    }

    private void startFragment(OperationFragment operationFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.consult_fragment, operationFragment)
                .addToBackStack(null)
                .commit();
    }

    public void editOperation(Operation operation) {
        OperationFragment operationFragment = OperationFragment.newInstance(account);
        operationFragment.setOperation(operation);

        startFragment(operationFragment);
    }
}
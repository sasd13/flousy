package com.sasd13.flousy.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.util.Extra;
import com.sasd13.flousy.fragment.account.AccountFragment;
import com.sasd13.flousy.fragment.operation.OperationFragment;
import com.sasd13.flousy.handler.ConsultHandler;
import com.sasd13.flousy.util.SessionHelper;

public class ConsultActivity extends MainActivity {

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
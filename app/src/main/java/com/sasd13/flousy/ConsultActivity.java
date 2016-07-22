package com.sasd13.flousy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.handler.consult.ConsultHandler;
import com.sasd13.flousy.fragment.consult.AccountFragment;
import com.sasd13.flousy.fragment.consult.OperationFragment;
import com.sasd13.flousy.util.SessionHelper;

public class ConsultActivity extends MotherActivity {

    private Account account;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consult);
        GUIHelper.colorTitles(this);

        ConsultHandler consultHandler = new ConsultHandler(this);
        account = consultHandler.readAccount(SessionHelper.getExtraId(this, Extra.CUSTOMER_ID));

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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.consult_fragment, OperationFragment.newInstance(account))
                .addToBackStack(null)
                .commit();
    }

    public void editOperation(Operation operation) {
        OperationFragment operationFragment = OperationFragment.newInstance(account);
        operationFragment.setOperation(operation);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.consult_fragment, operationFragment)
                .addToBackStack(null)
                .commit();
    }

    public void finishFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().remove(fragment).commit();
        fragmentManager.popBackStack();
    }
}
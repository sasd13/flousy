package com.sasd13.flousy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.handler.consult.ConsultHandler;
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
        AccountFragment accountFragment = AccountFragment.newInstance();
        accountFragment.setAccount(account);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.consult_fragment, accountFragment)
                .commit();
    }

    public void newOperation() {
        OperationFragment operationFragment = OperationFragment.newInstance();
        operationFragment.setAccount(account);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.consult_fragment, operationFragment)
                .addToBackStack(null)
                .commit();
    }

    public void editOperation(Operation operation) {
        OperationFragment operationFragment = OperationFragment.newInstance();
        operationFragment.setAccount(account);
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
package com.sasd13.flousy;

import android.os.Bundle;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.handler.consult.ConsultHandler;
import com.sasd13.flousy.fragment.consult.AccountFragment;
import com.sasd13.flousy.fragment.consult.OperationFragment;
import com.sasd13.flousy.util.SessionHelper;

public class ConsultActivity extends MotherActivity {

    private AccountFragment accountFragment;
    private Account account;
    private ConsultHandler consultHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consult);
        GUIHelper.colorTitles(this);

        consultHandler = new ConsultHandler(this);
        account = consultHandler.readAccount(SessionHelper.getExtraId(this, Extra.CUSTOMER_ID));

        buildView();
    }

    private void buildView() {
        accountFragment = (AccountFragment) getSupportFragmentManager().findFragmentById(R.id.consult_fragment);
        accountFragment.setAccount(account);
    }

    public void listOperations() {
        AccountFragment accountFragment = AccountFragment.newInstance();
        accountFragment.setAccount(account);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.consult_fragment, accountFragment)
                .addToBackStack(null)
                .commit();
    }

    public void newOperation() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.consult_fragment, OperationFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    public void notifyAddedOperation(Operation operation) {

    }

    public void editOperation(Operation operation) {
        OperationFragment operationFragment = OperationFragment.newInstance();
        operationFragment.setOperation(operation);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.consult_fragment, operationFragment)
                .addToBackStack(null)
                .commit();
    }

    public void notifyRemovedOperation(Operation operation) {
        account.removeOperation(operation);
    }
}
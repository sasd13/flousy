package com.sasd13.flousy.controller;

import com.sasd13.flousy.R;
import com.sasd13.flousy.activities.MainActivity;
import com.sasd13.flousy.fragment.IConsultController;
import com.sasd13.flousy.fragment.account.AccountFragment;
import com.sasd13.flousy.fragment.operation.OperationFragment;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public class ConsultController extends Controller implements IConsultController {

    public ConsultController(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void entry() {

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
}

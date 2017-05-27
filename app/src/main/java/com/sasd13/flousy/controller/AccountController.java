package com.sasd13.flousy.controller;

import com.sasd13.flousy.activities.MainActivity;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.view.fragment.IAccountController;
import com.sasd13.flousy.view.fragment.account.AccountFragment;
import com.sasd13.flousy.view.fragment.operation.OperationFragment;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public class AccountController extends Controller implements IAccountController {

    public AccountController(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void entry() {
        listOperations();
    }

    @Override
    public void listOperations() {
        startFragment(AccountFragment.newInstance());
    }

    @Override
    public void newOperation() {
        startFragment(OperationFragment.newInstance());
    }

    @Override
    public void createOperation(Operation operation) {

    }

    @Override
    public void showOperation(Operation operation) {

    }

    @Override
    public void updateOperation(Operation operation, Operation operationToUpdate) {

    }

    @Override
    public void deleteOperation(Operation operation) {

    }
}

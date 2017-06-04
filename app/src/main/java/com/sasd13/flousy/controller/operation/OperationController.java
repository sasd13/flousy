package com.sasd13.flousy.controller.operation;

import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.controller.Controller;
import com.sasd13.flousy.scope.OperationScope;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.service.IAccountService;
import com.sasd13.flousy.view.IOperationController;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public class OperationController extends Controller implements IOperationController {

    private OperationScope scope;
    private IAccountService accountService;

    public OperationController(MainActivity mainActivity, IAccountService accountService) {
        super(mainActivity);

        scope = new OperationScope();
        this.accountService = accountService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void actionNewOperation(Account account) {

    }

    @Override
    public void actionCreateOperation(Operation operation) {

    }

    @Override
    public void actionShowOperation(Operation operation) {

    }

    @Override
    public void actionRemoveOperation(Operation operation) {

    }
}

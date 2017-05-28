package com.sasd13.flousy.view.fragment.account;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.flousy.view.IOperationController;
import com.sasd13.flousy.view.gui.tab.OperationItemModel;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class OperationItemActionClick implements IAction {

    private OperationItemModel operationItemModel;
    private AccountActionModeProvider accountActionModeProvider;
    private IOperationController controller;

    public OperationItemActionClick(OperationItemModel operationItemModel, AccountActionModeProvider accountActionModeProvider, IOperationController controller) {
        this.operationItemModel = operationItemModel;
        this.accountActionModeProvider = accountActionModeProvider;
        this.controller = controller;
    }

    @Override
    public void execute() {
        if (!accountActionModeProvider.inActionMode()) {
            controller.actionShowOperation(operationItemModel.getOperation());
        } else {
            accountActionModeProvider.onClickOnModel(operationItemModel);
        }
    }
}

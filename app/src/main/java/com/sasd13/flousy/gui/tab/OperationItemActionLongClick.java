package com.sasd13.flousy.gui.tab;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.flousy.fragment.consult.AccountActionModeProvider;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class OperationItemActionLongClick implements IAction {

    private OperationItemModel operationItemModel;
    private AccountActionModeProvider accountActionModeProvider;

    public OperationItemActionLongClick(OperationItemModel operationItemModel, AccountActionModeProvider accountActionModeProvider) {
        this.operationItemModel = operationItemModel;
        this.accountActionModeProvider = accountActionModeProvider;
    }

    @Override
    public void execute() {
        if (!accountActionModeProvider.inActionMode()) {
            accountActionModeProvider.startActionMode();
        }

        accountActionModeProvider.onClickOnModel(operationItemModel);
    }
}

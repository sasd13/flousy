package com.sasd13.flousy.gui.tab;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.flousy.fragment.consult.AccountFragment;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class OperationItemActionLongClick implements IAction {

    private AccountFragment accountFragment;
    private OperationItemModel operationItemModel;

    public OperationItemActionLongClick(AccountFragment accountFragment, OperationItemModel operationItemModel) {
        this.accountFragment = accountFragment;
        this.operationItemModel = operationItemModel;
    }

    @Override
    public void execute() {
        if (!accountFragment.inActionMode()) {
            accountFragment.startActionMode();
        }

        accountFragment.onSelectModel(operationItemModel);
    }
}

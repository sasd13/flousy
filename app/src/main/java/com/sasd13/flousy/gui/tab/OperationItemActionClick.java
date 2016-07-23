package com.sasd13.flousy.gui.tab;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.flousy.ConsultActivity;
import com.sasd13.flousy.fragment.consult.AccountFragment;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class OperationItemActionClick implements IAction {

    private AccountFragment accountFragment;
    private OperationItemModel operationItemModel;

    public OperationItemActionClick(AccountFragment accountFragment, OperationItemModel operationItemModel) {
        this.accountFragment = accountFragment;
        this.operationItemModel = operationItemModel;
    }

    @Override
    public void execute() {
        if (!accountFragment.inActionMode()) {
            ((ConsultActivity) accountFragment.getActivity()).editOperation(operationItemModel.getOperation());
        } else {
            accountFragment.onSelectModel(operationItemModel);
        }
    }
}

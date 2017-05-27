package com.sasd13.flousy.view.fragment.account;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.flousy.activity.ConsultActivity;
import com.sasd13.flousy.view.gui.tab.OperationItemModel;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class OperationItemActionClick implements IAction {

    private OperationItemModel operationItemModel;
    private AccountActionModeProvider accountActionModeProvider;
    private ConsultActivity consultActivity;

    public OperationItemActionClick(OperationItemModel operationItemModel, AccountActionModeProvider accountActionModeProvider, ConsultActivity consultActivity) {
        this.operationItemModel = operationItemModel;
        this.accountActionModeProvider = accountActionModeProvider;
        this.consultActivity = consultActivity;
    }

    @Override
    public void execute() {
        if (!accountActionModeProvider.inActionMode()) {
            consultActivity.editOperation(operationItemModel.getOperation());
        } else {
            accountActionModeProvider.onClickOnModel(operationItemModel);
        }
    }
}

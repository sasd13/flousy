package com.sasd13.flousy.gui.tab;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.flousy.fragment.consult.AccountFragment;

import java.util.List;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class OperationItemActionLongClick implements IAction {

    private AccountFragment fragment;
    private OperationItemModel model;
    private List<OperationItemModel> models;
    private OperationItemActionMode callback;

    public OperationItemActionLongClick(AccountFragment fragment, OperationItemActionMode callback, OperationItemModel model, List<OperationItemModel> models) {
        this.fragment = fragment;
        this.model = model;
        this.models = models;
        this.callback = callback;
    }

    @Override
    public void execute() {
        if (!fragment.inActionMode()) {
            fragment.setActionMode(fragment.getActivity().startActionMode(callback));
        }

        if (!model.isSelected()) {
            model.setSelected(true);
            models.add(model);
        } else {
            model.setSelected(false);
            models.remove(model);
        }
    }
}

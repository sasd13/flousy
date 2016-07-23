package com.sasd13.flousy.fragment.consult;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.view.Menu;
import android.view.MenuItem;

import com.sasd13.androidex.context.ActionModeProvider;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.flousy.R;
import com.sasd13.flousy.gui.tab.OperationItemModel;

import java.util.ArrayList;
import java.util.List;

public class AccountActionModeProvider extends ActionModeProvider {

    private List<OperationItemModel> selectedModels;
    private MenuItem menuItemDelete;

    public AccountActionModeProvider(AccountFragment accountFragment) {
        super(accountFragment);

        selectedModels = new ArrayList<>();
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_context_operation;
    }

    @Override
    public void onCreateActionMode(Menu menu) {
        super.onCreateActionMode(menu);

        menuItemDelete = menu.findItem(R.id.menu_context_operation_action_delete);
    }

    public void onClickOnModel(OperationItemModel model) {
        if (!model.isSelected()) {
            selectedModels.add(model);
            model.setSelected(true);
        } else {
            selectedModels.remove(model);
            model.setSelected(false);
        }

        refreshActionModeBar();
    }

    private void refreshActionModeBar() {
        if (selectedModels.size() <= 1) {
            actionMode.setTitle(selectedModels.size() + " " + actionModeConsumer.getActivity().getResources().getString(R.string.message_selected));
        } else {
            actionMode.setTitle(selectedModels.size() + " " + actionModeConsumer.getActivity().getResources().getString(R.string.message_selected_many));
        }

        if (selectedModels.isEmpty()) {
            if (menuItemDelete.isVisible()) {
                menuItemDelete.setVisible(false);
            }
        } else {
            if (!menuItemDelete.isVisible()) {
                menuItemDelete.setVisible(true);
            }
        }
    }

    @Override
    public boolean onMenuItemSelected(@IdRes int menuItemId) {
        switch (menuItemId) {
            case R.id.menu_context_operation_action_delete:
                deleteSelectedModels();
                break;
            default:
                return false;
        }

        return true;
    }

    private void deleteSelectedModels() {
        OptionDialog.showOkCancelDialog(
                actionModeConsumer.getActivity(),
                actionModeConsumer.getActivity().getResources().getString(R.string.message_delete),
                actionModeConsumer.getActivity().getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((AccountFragment) actionModeConsumer).deleteOperations(selectedModels);
                        selectedModels.clear();
                        actionMode.finish();
                    }
                }
        );
    }

    @Override
    public void onDestroyActionMode() {
        super.onDestroyActionMode();

        resetSelectedModels();
    }

    private void resetSelectedModels() {
        for (OperationItemModel selectedModel : selectedModels) {
            selectedModel.setSelected(false);
        }

        selectedModels.clear();
    }
}
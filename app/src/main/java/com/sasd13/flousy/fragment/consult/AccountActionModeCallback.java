package com.sasd13.flousy.fragment.consult;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.flousy.R;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class AccountActionModeCallback implements ActionMode.Callback {

    private AccountFragment accountFragment;

    public AccountActionModeCallback(AccountFragment accountFragment) {
        this.accountFragment = accountFragment;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_context_operation, menu);

        accountFragment.onStartActionMode();

        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_context_operation_action_delete:
                accountFragment.deleteSelectedOperations();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        accountFragment.onFinishActionMode();
    }
}

package com.sasd13.flousy.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.sasd13.androidex.activity.DrawerActivity;
import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.drawer.EnumDrawerItemType;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.R;
import com.sasd13.flousy.fragment.IController;
import com.sasd13.flousy.gui.browser.Browser;
import com.sasd13.flousy.gui.browser.BrowserItemModel;
import com.sasd13.flousy.util.SessionHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DrawerActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected RecyclerHolder getDrawerHolder() {
        RecyclerHolder recyclerHolder = new RecyclerHolder();

        addBrowserItems(recyclerHolder);
        addAccountItems(recyclerHolder);

        return recyclerHolder;
    }

    private void addBrowserItems(RecyclerHolder recyclerHolder) {
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getItems(this);
        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            browserItemModel.setItemType(EnumDrawerItemType.NAV);

            pair = new RecyclerHolderPair(browserItemModel);
            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    startActivity(new Intent(MainActivity.this, browserItemModel.getTarget()));
                }
            });

            pairs.add(pair);
        }

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_menu), pairs);
    }

    public IController lookup(Class<? extends IController> mClass) {
        if (ISettingsController.class.isAssignableFrom(mClass)) {
            return settingsController;
        } else if (IProjectController.class.isAssignableFrom(mClass) || IRunningController.class.isAssignableFrom(mClass)) {
            return projectController;
        } else if (ITeamController.class.isAssignableFrom(mClass) || IStudentController.class.isAssignableFrom(mClass)) {
            return teamController;
        } else if (IRunningTeamController.class.isAssignableFrom(mClass)) {
            return runningTeamController;
        } else if (IReportController.class.isAssignableFrom(mClass)) {
            return reportController;
        } else {
            return null;
        }
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        BrowserItemModel browserItemModel = new BrowserItemModel(
                getResources().getString(R.string.drawer_label_logout),
                ContextCompat.getDrawable(this, R.drawable.ic_exit_to_app_black_24dp),
                ContextCompat.getColor(this, R.color.greyBackground),
                HomeActivity.class
        );
        browserItemModel.setItemType(EnumDrawerItemType.NAV);

        RecyclerHolderPair pair = new RecyclerHolderPair(browserItemModel);
        pair.addController(EnumActionEvent.CLICK, new IAction() {
            @Override
            public void execute() {
                SessionHelper.logOut(MainActivity.this);
            }
        });

        recyclerHolder.add(getResources().getString(R.string.drawer_header_account), pair);
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress()) {
            super.onBackPressed();

            if (!stack.isEmpty()) {
                stack.pop();

                if (!stack.isEmpty() && ProxyFragment.class.isAssignableFrom(stack.peek().getClass())) {
                    onBackPressed();
                }
            }
        }
    }

    public void startFragment(Fragment fragment) {
        stack.push(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void logOut() {
        OptionDialog.showOkCancelDialog(
                this,
                getResources().getString(R.string.button_logout),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        exit();
                    }
                }
        );
    }

    private void exit() {
        final WaitDialog waitDialog = new WaitDialog(this);
        final Intent intent = new Intent(this, LogInActivity.class);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                SessionHelper.clear(MainActivity.this);
                startActivity(intent);
                waitDialog.dismiss();
                finish();
            }
        }).start(GUIConstants.TIMEOUT_ACTIVITY);

        waitDialog.show();
    }

    public void clearHistory() {
        if (!stack.isEmpty()) {
            stack.clear();
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}

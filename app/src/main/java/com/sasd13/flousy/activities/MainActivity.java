package com.sasd13.flousy.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.sasd13.androidex.activity.DrawerActivity;
import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.R;
import com.sasd13.flousy.controller.AccountController;
import com.sasd13.flousy.controller.LogOutController;
import com.sasd13.flousy.controller.SettingsController;
import com.sasd13.flousy.view.fragment.HomeFragment;
import com.sasd13.flousy.view.fragment.IAccountController;
import com.sasd13.flousy.view.fragment.IController;
import com.sasd13.flousy.view.fragment.ILogOutController;
import com.sasd13.flousy.view.fragment.ISettingsController;
import com.sasd13.flousy.view.gui.browser.Browser;
import com.sasd13.flousy.view.gui.browser.BrowserItemModel;
import com.sasd13.flousy.util.SessionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends DrawerActivity {

    private ISettingsController settingsController;
    private IAccountController accountController;
    private ILogOutController logOutController;
    private IPagerHandler pagerHandler;
    private Stack<Fragment> stack = new Stack<>();

    public void setPagerHandler(IPagerHandler pagerHandler) {
        this.pagerHandler = pagerHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setContentView(R.layout.layout_container);
        showHome();
    }

    private void init() {
        settingsController = new SettingsController(this);
        accountController = new AccountController(this);
        logOutController = new LogOutController(this);
    }

    private void showHome() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, HomeFragment.newInstance())
                .commit();
    }

    @Override
    protected RecyclerHolder getDrawerHolder() {
        RecyclerHolder recyclerHolder = new RecyclerHolder();

        addNavItems(recyclerHolder);
        addAccountItems(recyclerHolder);

        return recyclerHolder;
    }

    private void addNavItems(RecyclerHolder recyclerHolder) {
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getNavItems(this);
        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            pair = new RecyclerHolderPair(browserItemModel);

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    lookup(browserItemModel.getTarget()).entry();
                    setDrawerOpened(false);
                }
            });

            pairs.add(pair);
        }

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_menu), pairs);
    }

    public IController lookup(Class<? extends IController> mClass) {
        if (ISettingsController.class.isAssignableFrom(mClass)) {
            return settingsController;
        } else if (IAccountController.class.isAssignableFrom(mClass)) {
            return accountController;
        } else if (ILogOutController.class.isAssignableFrom(mClass)) {
            return logOutController;
        } else {
            return null;
        }
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getAccountItems(this);
        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            pair = new RecyclerHolderPair(browserItemModel);

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    lookup(browserItemModel.getTarget()).entry();
                    setDrawerOpened(false);
                }
            });

            pairs.add(pair);
        }

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_account), pairs);
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress()) {
            super.onBackPressed();

            if (!stack.isEmpty()) {
                stack.pop();
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
        final Intent intent = new Intent(this, IdentityActivity.class);

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

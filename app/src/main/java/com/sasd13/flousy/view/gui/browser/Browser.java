package com.sasd13.flousy.view.gui.browser;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.sasd13.flousy.R;
import com.sasd13.flousy.view.fragment.IAccountController;
import com.sasd13.flousy.view.fragment.ILogOutController;
import com.sasd13.flousy.view.fragment.ISettingsController;

import java.util.ArrayList;
import java.util.List;

public class Browser {

    private static class BrowserHolder {
        private static final Browser INSTANCE = new Browser();
    }

    private List<BrowserItemModel> navItems, accountItems;

    private Browser() {
        navItems = new ArrayList<>();
        accountItems = new ArrayList<>();
    }

    public static Browser getInstance() {
        return BrowserHolder.INSTANCE;
    }

    public List<BrowserItemModel> getNavItems(final Context context) {
        if (navItems.isEmpty()) {
            navItems.add(new BrowserItemModel(
                    EnumBrowserItemType.ACCOUNT,
                    context.getResources().getString(R.string.activity_consult),
                    ContextCompat.getDrawable(context, R.drawable.griditem_consult),
                    ContextCompat.getColor(context, R.color.green),
                    IAccountController.class
            ));
        }

        return navItems;
    }

    public List<BrowserItemModel> getAccountItems(final Context context) {
        if (accountItems.isEmpty()) {
            accountItems.add(new BrowserItemModel(
                    EnumBrowserItemType.SETTINGS,
                    context.getResources().getString(R.string.title_settings),
                    ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp),
                    ContextCompat.getColor(context, R.color.brown),
                    ISettingsController.class
            ));
            accountItems.add(new BrowserItemModel(
                    EnumBrowserItemType.LOGOUT,
                    context.getResources().getString(R.string.drawer_label_logout),
                    ContextCompat.getDrawable(context, R.drawable.ic_exit_to_app_black_24dp),
                    ContextCompat.getColor(context, R.color.greyBackground),
                    ILogOutController.class
            ));
        }

        return accountItems;
    }
}

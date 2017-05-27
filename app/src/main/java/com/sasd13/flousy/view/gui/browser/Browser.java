package com.sasd13.flousy.view.gui.browser;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.sasd13.flousy.R;
import com.sasd13.flousy.view.IAccountController;
import com.sasd13.flousy.view.ILogOutController;
import com.sasd13.flousy.view.ISettingController;

import java.util.ArrayList;
import java.util.List;

public class Browser {

    private List<BrowserItemModel> navItems, accountItems;

    public Browser(Context context) {
        makeNavItems(context);
        makeAccountItems(context);
    }

    private void makeNavItems(Context context) {
        navItems = new ArrayList<>();

        navItems.add(new BrowserItemModel(
                context.getResources().getString(R.string.label_consult),
                ContextCompat.getDrawable(context, R.drawable.griditem_consult),
                ContextCompat.getColor(context, R.color.green),
                IAccountController.class
        ));
    }

    private void makeAccountItems(Context context) {
        accountItems = new ArrayList<>();

        accountItems.add(new BrowserItemModel(
                context.getResources().getString(R.string.title_settings),
                ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp),
                ContextCompat.getColor(context, R.color.brown),
                ISettingController.class
        ));
        accountItems.add(new BrowserItemModel(
                context.getResources().getString(R.string.label_logout),
                ContextCompat.getDrawable(context, R.drawable.ic_exit_to_app_black_24dp),
                ContextCompat.getColor(context, R.color.greyBackground),
                ILogOutController.class
        ));
    }

    public List<BrowserItemModel> getNavItems() {
        return navItems;
    }

    public List<BrowserItemModel> getAccountItems() {
        return accountItems;
    }
}

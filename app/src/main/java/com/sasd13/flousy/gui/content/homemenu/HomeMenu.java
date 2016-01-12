package com.sasd13.flousy.gui.content.homemenu;

import android.content.Context;
import android.content.Intent;

import com.sasd13.flousy.AccountActivity;
import com.sasd13.flousy.SettingActivity;
import com.sasd13.flousy.R;

public class HomeMenu {

    private static HomeMenu instance = null;

    private static final int SIZE = 2;
    private HomeMenuItem[] tab;

    private HomeMenu(Context context) {
        tab = new HomeMenuItem[SIZE];

        tab[0] = new HomeMenuItem(
                context.getResources().getString(R.string.activity_account),
                context.getResources().getDrawable(R.drawable.griditem_new),
                context.getResources().getColor(R.color.customGreen),
                new Intent(context, AccountActivity.class)
        );

        tab[1] = new HomeMenuItem(
                context.getResources().getString(R.string.activity_setting),
                context.getResources().getDrawable(R.drawable.griditem_settings),
                context.getResources().getColor(R.color.customBrown),
                new Intent(context, SettingActivity.class)
        );
    }

    public static synchronized HomeMenu getInstance(Context context) {
        if (instance == null) {
            instance = new HomeMenu(context);
        }

        return instance;
    }

    public HomeMenuItem[] getItems() { return tab; }
}

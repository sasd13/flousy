package com.sasd13.flousy.gui.nav;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.sasd13.flousy.AccountActivity;
import com.sasd13.flousy.SettingActivity;
import com.sasd13.flousy.R;

public class Nav {

    private static Nav instance = null;

    private static final int SIZE = 2;
    private NavItem[] tab;

    private Nav(Context context) {
        tab = new NavItem[SIZE];

        tab[0] = new NavItem(
                context.getResources().getString(R.string.activity_account),
                ContextCompat.getDrawable(context, R.drawable.griditem_new),
                ContextCompat.getColor(context, R.color.green),
                new Intent(context, AccountActivity.class)
        );

        tab[1] = new NavItem(
                context.getResources().getString(R.string.activity_setting),
                ContextCompat.getDrawable(context, R.drawable.griditem_settings),
                ContextCompat.getColor(context, R.color.brown),
                new Intent(context, SettingActivity.class)
        );
    }

    public static synchronized Nav getInstance(Context context) {
        if (instance == null) {
            instance = new Nav(context);
        }

        return instance;
    }

    public NavItem[] getItems() { return tab; }
}

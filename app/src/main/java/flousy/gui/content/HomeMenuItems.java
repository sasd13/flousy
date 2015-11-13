package flousy.gui.content;

import android.content.Context;
import android.content.Intent;

import com.example.flousy.AccountActivity;
import com.example.flousy.SettingActivity;
import com.example.flousy.R;

public class HomeMenuItems {

    private static HomeMenuItems instance = null;

    private static final int SIZE = 2;
    private HomeMenuItem[] tab;

    private HomeMenuItems(Context context) {
        this.tab = new HomeMenuItem[SIZE];

        this.tab[0] = new HomeMenuItem(
                context.getResources().getString(R.string.activity_account_name),
                context.getResources().getDrawable(R.drawable.griditem_new),
                context.getResources().getColor(R.color.customGreen),
                new Intent(context, AccountActivity.class)
        );

        this.tab[1] = new HomeMenuItem(
                context.getResources().getString(R.string.activity_setting_name),
                context.getResources().getDrawable(R.drawable.griditem_settings),
                context.getResources().getColor(R.color.customBrown),
                new Intent(context, SettingActivity.class)
        );
    }

    public static synchronized HomeMenuItems getInstance(Context context) {
        if (instance == null) {
            instance = new HomeMenuItems(context);
        }

        return instance;
    }

    public HomeMenuItem[] getItems() { return this.tab; }
}

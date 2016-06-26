package com.sasd13.flousy.gui.browser;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.sasd13.flousy.AccountActivity;
import com.sasd13.flousy.R;
import com.sasd13.flousy.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class Browser {

    public class Item {

        private String label;
        private Drawable icon;
        private int color;
        private Intent intent;

        private Item(String label, Drawable icon, int color, Intent intent) {
            this.label = label;
            this.icon = icon;
            this.color = color;
            this.intent = intent;
        }

        public String getLabel() {
            return label;
        }

        public Drawable getIcon() {
            return icon;
        }

        public int getColor() {
            return color;
        }

        public Intent getIntent() {
            return intent;
        }
    }

    private static class BrowserHolder {
        private static final Browser INSTANCE = new Browser();
    }

    private List<Item> items;

    private Browser() {
        items = new ArrayList<>();
    }

    public static Browser getInstance() {
        return BrowserHolder.INSTANCE;
    }

    public Item[] getItems() { return items.toArray(new Item[items.size()]); }

    public void init(Context context) {
        items.clear();

        items.add(new Item(
                context.getResources().getString(R.string.activity_account),
                ContextCompat.getDrawable(context, R.drawable.griditem_new),
                ContextCompat.getColor(context, R.color.green),
                new Intent(context, AccountActivity.class)
        ));

        items.add(new Item(
                context.getResources().getString(R.string.activity_setting),
                ContextCompat.getDrawable(context, R.drawable.griditem_settings),
                ContextCompat.getColor(context, R.color.brown),
                new Intent(context, SettingActivity.class)
        ));
    }
}

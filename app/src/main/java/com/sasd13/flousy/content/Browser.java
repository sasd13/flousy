package com.sasd13.flousy.content;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.sasd13.flousy.ConsultActivity;
import com.sasd13.flousy.FinancesActivity;
import com.sasd13.flousy.FriendsActivity;
import com.sasd13.flousy.OffersActivity;
import com.sasd13.flousy.OperationActivity;
import com.sasd13.flousy.R;
import com.sasd13.flousy.SettingsActivity;

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
                context.getResources().getString(R.string.activity_operation),
                ContextCompat.getDrawable(context, R.drawable.griditem_new),
                ContextCompat.getColor(context, R.color.green),
                new Intent(context, OperationActivity.class)
        ));

        items.add(new Item(
                context.getResources().getString(R.string.activity_consult),
                ContextCompat.getDrawable(context, R.drawable.griditem_consult),
                ContextCompat.getColor(context, R.color.red),
                new Intent(context, ConsultActivity.class)
        ));

        items.add(new Item(
                context.getResources().getString(R.string.activity_finances),
                ContextCompat.getDrawable(context, R.drawable.griditem_finances),
                ContextCompat.getColor(context, R.color.orange),
                new Intent(context, FinancesActivity.class)
        ));

        items.add(new Item(
                context.getResources().getString(R.string.activity_friends),
                ContextCompat.getDrawable(context, R.drawable.griditem_dividing),
                ContextCompat.getColor(context, R.color.blue),
                new Intent(context, FriendsActivity.class)
        ));

        items.add(new Item(
                context.getResources().getString(R.string.activity_offers),
                ContextCompat.getDrawable(context, R.drawable.griditem_evolution),
                ContextCompat.getColor(context, R.color.purple),
                new Intent(context, OffersActivity.class)
        ));

        items.add(new Item(
                context.getResources().getString(R.string.activity_settings),
                ContextCompat.getDrawable(context, R.drawable.griditem_settings),
                ContextCompat.getColor(context, R.color.brown),
                new Intent(context, SettingsActivity.class)
        ));
    }
}

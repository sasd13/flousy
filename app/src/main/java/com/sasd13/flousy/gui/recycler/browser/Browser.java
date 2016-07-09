package com.sasd13.flousy.gui.recycler.browser;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.sasd13.flousy.R;

import java.util.ArrayList;
import java.util.List;

public class Browser {

    private static class BrowserHolder {
        private static final Browser INSTANCE = new Browser();
    }

    private List<BrowserItemModel> items;

    private Browser() {
        items = new ArrayList<>();
    }

    public static Browser getInstance() {
        return BrowserHolder.INSTANCE;
    }

    public List<BrowserItemModel> getItems(final Context context) {
        if (items.isEmpty()) {
            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_operation),
                    ContextCompat.getDrawable(context, R.drawable.griditem_new),
                    ContextCompat.getColor(context, R.color.green)
            ));

            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_consult),
                    ContextCompat.getDrawable(context, R.drawable.griditem_consult),
                    ContextCompat.getColor(context, R.color.red)
            ));

            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_finances),
                    ContextCompat.getDrawable(context, R.drawable.griditem_finances),
                    ContextCompat.getColor(context, R.color.orange)
            ));

            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_friends),
                    ContextCompat.getDrawable(context, R.drawable.griditem_dividing),
                    ContextCompat.getColor(context, R.color.blue)
            ));

            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_offers),
                    ContextCompat.getDrawable(context, R.drawable.griditem_evolution),
                    ContextCompat.getColor(context, R.color.purple)
            ));

            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_settings),
                    ContextCompat.getDrawable(context, R.drawable.griditem_settings),
                    ContextCompat.getColor(context, R.color.brown)
            ));
        }

        return items;
    }
}

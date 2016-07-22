package com.sasd13.flousy.gui.browser;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.sasd13.flousy.ConsultActivity;
import com.sasd13.flousy.FinancesActivity;
import com.sasd13.flousy.FriendsActivity;
import com.sasd13.flousy.R;
import com.sasd13.flousy.SettingsActivity;

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
                    context.getResources().getString(R.string.activity_consult),
                    ContextCompat.getDrawable(context, R.drawable.griditem_consult),
                    ContextCompat.getColor(context, R.color.green),
                    ConsultActivity.class
            ));

            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_finances),
                    ContextCompat.getDrawable(context, R.drawable.griditem_finances),
                    ContextCompat.getColor(context, R.color.orange),
                    FinancesActivity.class
            ));

            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_friends),
                    ContextCompat.getDrawable(context, R.drawable.griditem_dividing),
                    ContextCompat.getColor(context, R.color.purple),
                    FriendsActivity.class
            ));

            items.add(new BrowserItemModel(
                    context.getResources().getString(R.string.activity_settings),
                    ContextCompat.getDrawable(context, R.drawable.griditem_settings),
                    ContextCompat.getColor(context, R.color.brown),
                    SettingsActivity.class
            ));
        }

        return items;
    }
}

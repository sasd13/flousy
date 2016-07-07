package com.sasd13.flousy.gui.recycler.browser;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.sasd13.androidex.gui.Action;
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

    private static class BrowserHolder {
        private static final Browser INSTANCE = new Browser();
    }

    private List<BrowserModel> items;

    private Browser() {
        items = new ArrayList<>();
    }

    public static Browser getInstance() {
        return BrowserHolder.INSTANCE;
    }

    public List<BrowserModel> getItems(final Context context) {
        if (items.isEmpty()) {
            items.add(new BrowserModel(
                    context.getResources().getString(R.string.activity_operation),
                    ContextCompat.getDrawable(context, R.drawable.griditem_new),
                    ContextCompat.getColor(context, R.color.green),
                    new Action() {
                        @Override
                        public void execute() {
                            context.startActivity(new Intent(context, OperationActivity.class));
                        }
                    }
            ));

            items.add(new BrowserModel(
                    context.getResources().getString(R.string.activity_consult),
                    ContextCompat.getDrawable(context, R.drawable.griditem_consult),
                    ContextCompat.getColor(context, R.color.red),
                    new Action() {
                        @Override
                        public void execute() {
                            context.startActivity(new Intent(context, ConsultActivity.class));
                        }
                    }
            ));

            items.add(new BrowserModel(
                    context.getResources().getString(R.string.activity_finances),
                    ContextCompat.getDrawable(context, R.drawable.griditem_finances),
                    ContextCompat.getColor(context, R.color.orange),
                    new Action() {
                        @Override
                        public void execute() {
                            context.startActivity(new Intent(context, FinancesActivity.class));
                        }
                    }
            ));

            items.add(new BrowserModel(
                    context.getResources().getString(R.string.activity_friends),
                    ContextCompat.getDrawable(context, R.drawable.griditem_dividing),
                    ContextCompat.getColor(context, R.color.blue),
                    new Action() {
                        @Override
                        public void execute() {
                            context.startActivity(new Intent(context, FriendsActivity.class));
                        }
                    }
            ));

            items.add(new BrowserModel(
                    context.getResources().getString(R.string.activity_offers),
                    ContextCompat.getDrawable(context, R.drawable.griditem_evolution),
                    ContextCompat.getColor(context, R.color.purple),
                    new Action() {
                        @Override
                        public void execute() {
                            context.startActivity(new Intent(context, OffersActivity.class));
                        }
                    }
            ));

            items.add(new BrowserModel(
                    context.getResources().getString(R.string.activity_settings),
                    ContextCompat.getDrawable(context, R.drawable.griditem_settings),
                    ContextCompat.getColor(context, R.color.brown),
                    new Action() {
                        @Override
                        public void execute() {
                            context.startActivity(new Intent(context, SettingsActivity.class));
                        }
                    }
            ));
        }

        return items;
    }
}

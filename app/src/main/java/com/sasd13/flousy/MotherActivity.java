package com.sasd13.flousy;

import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.drawer.EnumDrawerItemType;
import com.sasd13.flousy.gui.browser.Browser;
import com.sasd13.flousy.gui.browser.BrowserItemModel;
import com.sasd13.flousy.util.SessionHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class MotherActivity extends DrawerActivity {

    @Override
    protected RecyclerHolder getDrawerHolder() {
        RecyclerHolder recyclerHolder = new RecyclerHolder();

        addBrowserItems(recyclerHolder);
        addAccountItems(recyclerHolder);

        return recyclerHolder;
    }

    private void addBrowserItems(RecyclerHolder recyclerHolder) {
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getItems(this);
        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            browserItemModel.setItemType(EnumDrawerItemType.NAV);

            pair = new RecyclerHolderPair(browserItemModel);
            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    startActivity(new Intent(MotherActivity.this, browserItemModel.getTarget()));
                }
            });

            pairs.add(pair);
        }

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_menu), pairs);
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        BrowserItemModel browserItemModel = new BrowserItemModel(
                getResources().getString(R.string.drawer_label_logout),
                ContextCompat.getDrawable(this, R.drawable.ic_exit_to_app_black_24dp),
                ContextCompat.getColor(this, R.color.greyBackground),
                HomeActivity.class
        );
        browserItemModel.setItemType(EnumDrawerItemType.NAV);

        RecyclerHolderPair pair = new RecyclerHolderPair(browserItemModel);
        pair.addController(EnumActionEvent.CLICK, new IAction() {
            @Override
            public void execute() {
                SessionHelper.logOut(MotherActivity.this);
            }
        });

        recyclerHolder.add(getResources().getString(R.string.drawer_header_account), pair);
    }
}

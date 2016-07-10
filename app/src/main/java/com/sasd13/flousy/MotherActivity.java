package com.sasd13.flousy;

import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.ActionEvent;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemType;
import com.sasd13.flousy.gui.recycler.browser.Browser;
import com.sasd13.flousy.gui.recycler.browser.BrowserItemModel;
import com.sasd13.flousy.util.SessionHelper;

public abstract class MotherActivity extends DrawerActivity {

    @Override
    protected RecyclerHolder getDrawerHolder() {
        RecyclerHolder recyclerHolder = new RecyclerHolder();

        addBrowserItems(recyclerHolder);
        addAccountItems(recyclerHolder);

        return recyclerHolder;
    }

    private void addBrowserItems(RecyclerHolder recyclerHolder) {
        String menu = getResources().getString(R.string.drawer_header_menu);
        Browser browser = Browser.getInstance();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browser.getItems(this)) {
            browserItemModel.setType(RecyclerItemType.DRAWER_NAV);

            pair = new RecyclerHolderPair(browserItemModel);
            pair.addController(ActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    startActivity(new Intent(MotherActivity.this, browserItemModel.getTarget()));
                }
            });

            recyclerHolder.add(menu, pair);
        }
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        final BrowserItemModel browserItemModel = new BrowserItemModel(
                getResources().getString(R.string.drawer_label_logout),
                ContextCompat.getDrawable(this, R.drawable.ic_room_black_36dp),
                ContextCompat.getColor(this, R.color.greyBackground),
                HomeActivity.class
        );
        browserItemModel.setType(RecyclerItemType.DRAWER);

        RecyclerHolderPair pair = new RecyclerHolderPair(browserItemModel);
        pair.addController(ActionEvent.CLICK, new IAction() {
            @Override
            public void execute() {
                SessionHelper.logOut(MotherActivity.this);
            }
        });

        recyclerHolder.add(getResources().getString(R.string.drawer_header_account), pair);
    }
}

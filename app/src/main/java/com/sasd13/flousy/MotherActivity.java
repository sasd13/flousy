package com.sasd13.flousy;

import android.support.v4.content.ContextCompat;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemType;
import com.sasd13.flousy.gui.recycler.browser.Browser;
import com.sasd13.flousy.gui.recycler.browser.BrowserModel;
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

        for (BrowserModel browserModel : browser.getItems(this)) {
            browserModel.setItemType(RecyclerItemType.DRAWER_NAV);

            recyclerHolder.add(menu, browserModel);
        }
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        BrowserModel browserModel = new BrowserModel(
                getResources().getString(R.string.drawer_label_logout),
                ContextCompat.getDrawable(this, R.drawable.ic_room_black_36dp),
                ContextCompat.getColor(this, R.color.greyBackground),
                new Action() {
                    @Override
                    public void execute() {
                        SessionHelper.logOut(MotherActivity.this);
                    }
                }
        );
        browserModel.setItemType(RecyclerItemType.DRAWER);

        recyclerHolder.add(getResources().getString(R.string.drawer_header_account), browserModel);
    }
}

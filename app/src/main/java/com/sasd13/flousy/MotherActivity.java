package com.sasd13.flousy;

import android.support.v4.content.ContextCompat;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemType;
import com.sasd13.flousy.gui.recycler.browser.Browser;
import com.sasd13.flousy.gui.recycler.browser.BrowserItemModel;

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

        for (BrowserItemModel browserItemModel : browser.getItems(this)) {
            browserItemModel.setItemType(RecyclerItemType.DRAWER_NAV);

            recyclerHolder.add(menu, browserItemModel);
        }
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        BrowserItemModel browserItemModel = new BrowserItemModel(
                getResources().getString(R.string.drawer_label_logout),
                ContextCompat.getDrawable(this, R.drawable.ic_room_black_36dp),
                ContextCompat.getColor(this, R.color.greyBackground)
        );
        browserItemModel.setItemType(RecyclerItemType.DRAWER);

        recyclerHolder.add(getResources().getString(R.string.drawer_header_account), browserItemModel);
    }
}

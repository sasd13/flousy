package com.sasd13.flousy;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerModel;
import com.sasd13.androidex.gui.widget.recycler.drawer.NavModel;
import com.sasd13.flousy.gui.Browser;
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
        Browser.Item[] items = Browser.getInstance().getItems();
        NavModel[] navModels = new NavModel[items.length];

        int i=-1;

        for (final Browser.Item item : items) {
            i++;

            navModels[i] = new NavModel();

            navModels[i].setIcon(item.getIcon());
            navModels[i].setLabel(item.getLabel());
            navModels[i].setActionClick(new Action() {
                @Override
                public void execute() {
                    startActivity(item.getIntent());
                }
            });
        }

        recyclerHolder.add("Menu", navModels);
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        DrawerModel drawerModel = new DrawerModel();
        drawerModel.setLabel("Déconnexion");
        drawerModel.setActionClick(new Action() {
            @Override
            public void execute() {
                SessionHelper.logOut(MotherActivity.this);
            }
        });

        recyclerHolder.add("Compte", new DrawerModel[]{ drawerModel });
    }
}

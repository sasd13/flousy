package com.sasd13.flousy;

import android.content.Intent;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHeader;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.drawer.Drawer;
import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItem;
import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItemNav;
import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerModel;
import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerModelNav;
import com.sasd13.flousy.gui.nav.Nav;
import com.sasd13.flousy.gui.nav.NavItem;
import com.sasd13.flousy.util.SessionHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class MotherActivity extends DrawerActivity {

    @Override
    protected void fillDrawer(Drawer drawer) {
        addItemsNav(drawer);

        addItemsAccount(drawer);
    }

    private void addItemsNav(Drawer drawer) {
        Nav nav = Nav.getInstance(this);

        List<DrawerItem> drawerItems = new ArrayList<>();
        DrawerItemNav drawerItemNav;
        DrawerModelNav drawerModelNav;

        for (final NavItem navItem : nav.getItems()) {
            drawerItemNav = new DrawerItemNav();
            drawerModelNav = new DrawerModelNav();

            drawerModelNav.setIcon(navItem.getIcon());
            drawerModelNav.setLabel(navItem.getText());

            drawerItemNav.setOnClickListener(new RecyclerItem.OnClickListener() {
                @Override
                public void onClick(RecyclerItem recyclerItem) {
                    navItem.getIntent().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(navItem.getIntent());
                }
            });

            drawerModelNav.addObserver(drawerItemNav);

            drawerItems.add(drawerItemNav);
        }

        drawer.addAll(new RecyclerHeader("Menu"), drawerItems);
    }

    private void addItemsAccount(Drawer drawer) {
        DrawerItem drawerItem = new DrawerItem();
        DrawerModel drawerModel = new DrawerModel();

        drawerModel.setLabel("Déconnexion");
        drawerItem.setOnClickListener(new RecyclerItem.OnClickListener() {
            @Override
            public void onClick(RecyclerItem recyclerItem) {
                SessionHelper.logOut(MotherActivity.this);
            }
        });

        drawerModel.addObserver(drawerItem);

        drawer.addAll(new RecyclerHeader("Compte"), new DrawerItem[]{ drawerItem });
    }
}

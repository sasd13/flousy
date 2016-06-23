package com.sasd13.flousy;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;

public abstract class MotherActivity extends DrawerActivity {

    @Override
    protected RecyclerHolder getDrawerHolder() {

        return null;
    }

    /*private void addItemsNav(Drawer drawer) {
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
    }*/
}

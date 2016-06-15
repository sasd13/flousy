package com.sasd13.flousy;

import android.content.Intent;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItem;
import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItemNav;
import com.sasd13.flousy.gui.nav.Nav;
import com.sasd13.flousy.gui.nav.NavItem;
import com.sasd13.flousy.util.SessionHelper;

public abstract class MotherActivity extends DrawerActivity {

    @Override
    protected void fillDrawer() {
        /*DrawerItemTitle drawerItemTitle = new DrawerItemTitle();
        drawerItemTitle.setText(getResources().getString(R.string.activity_home));
        drawer.addItem(drawerItemTitle);*/

        addNavItemsToDrawer();
    }

    private void addNavItemsToDrawer() {
        Nav nav = Nav.getInstance(this);
        DrawerItemNav drawerItemNav;

        for (NavItem navItem : nav.getItems()) {
            drawerItemNav = new DrawerItemNav();

            drawerItemNav.setTag(navItem);
            drawerItemNav.setColor(navItem.getColor());
            drawerItemNav.setLabel(navItem.getText());
            drawerItemNav.setOnClickListener(new RecyclerItem.OnClickListener() {
                @Override
                public void onClickOnRecyclerItem(RecyclerItem recyclerItem) {
                    NavItem navItem = (NavItem) recyclerItem.getTag();

                    navItem.getIntent().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(navItem.getIntent());
                }
            });

            drawer.addItem(drawerItemNav);
        }

        DrawerItem drawerItem = new DrawerItem();
        drawerItem.setLabel("Déconnexion");
        drawerItem.setOnClickListener(new RecyclerItem.OnClickListener() {
            @Override
            public void onClickOnRecyclerItem(RecyclerItem recyclerItem) {
                SessionHelper.logOut(MotherActivity.this);
            }
        });

        drawer.addItem(drawerItem);
    }
}

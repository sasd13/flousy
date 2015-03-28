package com.diderot.android.flousy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import flousy.gui.actionbar.ActionBar;
import flousy.gui.activitybar.ActivityBar;
import flousy.gui.activitybar.ActivityBarFactory;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.color.ColorBrightness;
import flousy.gui.content.ColorCustomizer;
import flousy.gui.content.DimensionCustomizer;
import flousy.gui.content.TextCustomizer;
import flousy.gui.drawer.MenuDrawerItem;
import flousy.gui.drawer.Drawer;
import flousy.gui.drawer.DrawerItemTitle;

public class MotherActivity extends Activity implements ColorCustomizer, TextCustomizer, DimensionCustomizer {

    public static final int APP_COLOR = R.color.customGreenApp;

    private int activityColor;

    private ActionBar actionBar;

    private Drawer drawer;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerView;

    private ActivityBar activityBar;
    private View contentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        super.setContentView(R.layout.activity_mother);

        //Create ActivityColor with app color
        this.activityColor = getResources().getColor(APP_COLOR);

        //Create CustomActionBar
        ViewStub actionbarStub = (ViewStub) findViewById(R.id.actionbar_viewstub);
        this.actionBar = new ActionBar(this);
        this.actionBar.inflate(actionbarStub);

        //Create Drawer
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawerView = (RecyclerView) findViewById(R.id.drawer_view);
        this.drawer = new Drawer();
        this.actionBar.setDrawerEnabled(true);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        this.drawerView.setHasFixedSize(true);

        // use a linear layout manager
        this.drawerView.setLayoutManager(new LinearLayoutManager(this));

        //Set adapter
        this.drawer.adapt(drawerView);

        //Add Items menu
        DrawerItemTitle drawerItemTitle = new DrawerItemTitle();
        drawerItemTitle.setTitle(getResources().getString(R.string.activity_menu_name));
        this.drawer.addItem(drawerItemTitle);

        MenuDrawerItem menuDrawerItem = null;
        for(int i=0; i<6; i++) {
            switch (i) {
                case 0:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(getResources().getString(R.string.activity_new_name));
                    menuDrawerItem.setIntent(new Intent(this, NewActivity.class));
                    menuDrawerItem.setColor(getResources().getColor(NewActivity.ACTIVITY_COLOR));
                    break;
                case 1:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(getResources().getString(R.string.activity_consult_name));
                    menuDrawerItem.setIntent(new Intent(this, ConsultActivity.class));
                    menuDrawerItem.setColor(getResources().getColor(ConsultActivity.ACTIVITY_COLOR));
                    break;
                case 2:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(getResources().getString(R.string.activity_finances_name));
                    menuDrawerItem.setIntent(new Intent(this, FinancesActivity.class));
                    menuDrawerItem.setColor(getResources().getColor(FinancesActivity.ACTIVITY_COLOR));
                    break;
                case 3:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(getResources().getString(R.string.activity_friends_name));
                    menuDrawerItem.setIntent(new Intent(this, FriendsActivity.class));
                    menuDrawerItem.setColor(getResources().getColor(FriendsActivity.ACTIVITY_COLOR));
                    break;
                case 4:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(getResources().getString(R.string.activity_offers_name));
                    menuDrawerItem.setIntent(new Intent(this, OffersActivity.class));
                    menuDrawerItem.setColor(getResources().getColor(OffersActivity.ACTIVITY_COLOR));
                    break;
                case 5:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(getResources().getString(R.string.activity_settings_name));
                    menuDrawerItem.setIntent(new Intent(this, SettingsActivity.class));
                    menuDrawerItem.setColor(getResources().getColor(SettingsActivity.ACTIVITY_COLOR));
                    break;
            }
            this.drawer.addItem(menuDrawerItem);
        }

        //Add ActionBar drawer button listener
        this.actionBar.getDrawerButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(drawerView)) {
                    drawerLayout.closeDrawer(drawerView);
                } else {
                    drawerLayout.openDrawer(drawerView);
                }
            }
        });

        //Customize activity default
        customizeColor();
        customizeText();
        customizeDimensions();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(this.drawerLayout.isDrawerOpen(this.drawerView)) {
            this.drawerLayout.closeDrawer(this.drawerView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Disable re-onCreate for subclasses on up button click
        return super.onOptionsItemSelected(item);
    }

    public int getActivityColor() {
        return this.activityColor;
    }

    public void setActivityColor(int activityColor) {
        this.activityColor = activityColor;
    }

    public ActionBar getCustomActionBar() {
        return this.actionBar;
    }

    public Drawer getDrawer() {
        return this.drawer;
    }

    public ActivityBar getActivityBar() {
        return this.activityBar;
    }

    public ActivityBar createActivityBar(ActivityBarType type) {
        this.activityBar = ActivityBarFactory.create(type);

        if(this.activityBar != null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.activitybar_viewstub);
            activityBar.inflate(viewStub);
            activityBar.getView().setBackgroundColor(this.activityColor);
        }

        return this.activityBar;
    }

    public View getContentView() {
        return this.contentView;
    }

    @Override
    public void setContentView(int layoutResID) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(layoutResID);
        this.contentView = viewStub.inflate();
    }

    @Override
    public void customizeColor() {
        this.actionBar.setColor(this.activityColor);

        if(this.activityBar != null) {
            this.activityBar.getView().setBackgroundColor(ColorBrightness.colorDarker(this.activityColor));
        }

        if(this.contentView != null) {
            int i = 0;
            String tag = "customize_";
            View viewChild = this.contentView.findViewWithTag(tag + i);
            while (viewChild != null) {
                if(viewChild.getClass().getSimpleName().compareTo("TextView") == 0) {
                    ((TextView) viewChild).setTextColor(this.activityColor);
                } else if(viewChild.getClass().getSimpleName().compareTo("Button") == 0) {
                    ((Button) viewChild).setTextColor(Color.WHITE);
                    viewChild.setBackgroundColor(this.activityColor);
                } else {
                    viewChild.setBackgroundColor(this.activityColor);
                }

                i++;
                viewChild = this.contentView.findViewWithTag(tag + i);
            }
        }
    }

    @Override
    public void customizeText() {
        if(this.contentView != null) {
            int i = 0;
            String tag = "customize_";
            View viewChild = this.contentView.findViewWithTag(tag + i);
            while (viewChild != null) {
                if(viewChild.getClass().getSimpleName().compareTo("TextView") == 0) {
                    CharSequence text = ((TextView) viewChild).getText();
                    ((TextView) viewChild).setText(text.toString().toUpperCase());
                    ((TextView) viewChild).setTypeface(Typeface.DEFAULT_BOLD);
                } else if(viewChild.getClass().getSimpleName().compareTo("Button") == 0) {
                    ((Button) viewChild).setTextSize(TypedValue.DENSITY_DEFAULT, getResources().getDimension(R.dimen.textsize_medium));
                    ((Button) viewChild).setTypeface(Typeface.DEFAULT_BOLD);
                }

                i++;
                viewChild = this.contentView.findViewWithTag(tag + i);
            }
        }
    }

    @Override
    public void customizeDimensions() {
        if(this.contentView != null) {
            int i = 0;
            String tag = "customize_";
            View viewChild = this.contentView.findViewWithTag(tag + i);
            while (viewChild != null) {
                if(viewChild.getClass().getSimpleName().compareTo("TextView") == 0) {
                    viewChild.setPadding(
                            (int) getResources().getDimension(R.dimen.activitycontent_padding),
                            0,
                            0,
                            0);
                } else if(viewChild.getClass().getSimpleName().compareTo("Button") == 0) {
                    viewChild.setPadding(
                            (int) getResources().getDimension(R.dimen.button_horizontalpadding),
                            (int) getResources().getDimension(R.dimen.button_verticalpadding),
                            (int) getResources().getDimension(R.dimen.button_horizontalpadding),
                            (int) getResources().getDimension(R.dimen.button_verticalpadding));
                }

                i++;
                viewChild = this.contentView.findViewWithTag(tag + i);
            }
        }
    }
}

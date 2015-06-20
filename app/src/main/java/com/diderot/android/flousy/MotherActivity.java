package com.diderot.android.flousy;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
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
import flousy.gui.activitybar.ActivityBarException;
import flousy.gui.activitybar.ActivityBarFactory;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.content.FlousyMenu;
import flousy.gui.content.ColorCustomizer;
import flousy.gui.content.DimensionCustomizer;
import flousy.gui.content.TextCustomizer;
import flousy.gui.color.ColorOnTouchListener;
import flousy.gui.content.ListFlousyMenus;
import flousy.gui.widget.recycler.drawer.DrawerItemMenu;
import flousy.gui.widget.recycler.drawer.Drawer;
import flousy.gui.widget.recycler.drawer.DrawerItemTitle;
import flousy.session.Session;

public class MotherActivity extends Activity implements ColorCustomizer, TextCustomizer, DimensionCustomizer {

    public static final int APP_COLOR = R.color.customGreenApp;

    private int activityColor;
    private ActionBar actionBar;
    private Drawer drawer;
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
        this.actionBar.setSubTitleViewEnabled(false);

        //Create Drawer
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawer = new Drawer(this, drawerLayout);

        RecyclerView drawerView = (RecyclerView) findViewById(R.id.drawer_view);
        this.drawer.adapt(drawerView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.drawer.setOpened(false);
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

    @Override
    public void setContentView(int layoutResource) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(layoutResource);
        this.contentView = viewStub.inflate();
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
        try {
            this.activityBar = ActivityBarFactory.create(type);
            this.activityBar.inflate((ViewStub) findViewById(R.id.activitybar_viewstub));
            this.activityBar.setColor(this.activityColor);
        } catch (ActivityBarException e) {
            e.printStackTrace();
        }

        return this.activityBar;
    }

    public void initialize() {
        if (Session.isLogged()) {
            addDrawerItems();
        }

        customizeColor();
        customizeDimensions();
        customizeText();
    }

    private void addDrawerItems() {
        DrawerItemTitle drawerItemTitle = new DrawerItemTitle();
        drawerItemTitle.setText(getResources().getString(R.string.activity_menu_name));
        this.drawer.addItem(drawerItemTitle);

        ListFlousyMenus listFlousyMenus = ListFlousyMenus.getInstance(this);

        DrawerItemMenu drawerItemMenu;
        for(Object flousyMenu : listFlousyMenus) {
            drawerItemMenu = new DrawerItemMenu();

            drawerItemMenu.setColor(((FlousyMenu) flousyMenu).getColor());
            drawerItemMenu.setText(((FlousyMenu) flousyMenu).getName());
            drawerItemMenu.setIntent(((FlousyMenu) flousyMenu).getIntent());

            this.drawer.addItem(drawerItemMenu);
        }
    }

    @Override
    public void customizeColor() {
        this.actionBar.setColor(this.activityColor);

        ColorOnTouchListener listener = new ColorOnTouchListener(this.activityColor);

        this.actionBar.getLayoutActionUp().setOnTouchListener(listener);
        this.actionBar.getActionFirstButton().setOnTouchListener(listener);
        this.actionBar.getActionSecondButton().setOnTouchListener(listener);
        this.actionBar.getActionDrawerButton().setOnTouchListener(listener);

        try {
            this.activityBar.setColor(this.activityColor);

            int i = 0;
            String tag = "customize_";
            View viewChild = this.contentView.findViewWithTag(tag + i);

            while (viewChild != null) {
                if (viewChild.getClass().getSimpleName().compareTo("TextView") == 0) {
                    ((TextView) viewChild).setTextColor(this.activityColor);
                } else if (viewChild.getClass().getSimpleName().compareTo("Button") == 0) {
                    ((Button) viewChild).setTextColor(Color.WHITE);
                    viewChild.setBackgroundColor(this.activityColor);
                } else {
                    viewChild.setBackgroundColor(this.activityColor);
                }

                i++;
                viewChild = this.contentView.findViewWithTag(tag + i);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void customizeText() {
        try {
            int i = 0;
            String tag = "customize_";
            View viewChild = this.contentView.findViewWithTag(tag + i);

            while (viewChild != null) {
                if (viewChild.getClass().getSimpleName().compareTo("TextView") == 0) {
                    CharSequence text = ((TextView) viewChild).getText();
                    ((TextView) viewChild).setText(text.toString().toUpperCase());
                    ((TextView) viewChild).setTypeface(Typeface.DEFAULT_BOLD);
                } else if (viewChild.getClass().getSimpleName().compareTo("Button") == 0) {
                    ((Button) viewChild).setTextSize(TypedValue.DENSITY_DEFAULT, getResources().getDimension(R.dimen.textsize_medium));
                    ((Button) viewChild).setTypeface(Typeface.DEFAULT_BOLD);
                }

                i++;
                viewChild = this.contentView.findViewWithTag(tag + i);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void customizeDimensions() {
        try {
            int i = 0;
            String tag = "customize_";
            View viewChild = this.contentView.findViewWithTag(tag + i);

            while (viewChild != null) {
                if (viewChild.getClass().getSimpleName().compareTo("TextView") == 0) {
                    viewChild.setPadding(
                            getResources().getDimensionPixelSize(R.dimen.activitycontent_padding),
                            0,
                            0,
                            0);
                } else if (viewChild.getClass().getSimpleName().compareTo("Button") == 0) {
                    viewChild.setPadding(
                            getResources().getDimensionPixelSize(R.dimen.button_horizontalpadding),
                            getResources().getDimensionPixelSize(R.dimen.button_verticalpadding),
                            getResources().getDimensionPixelSize(R.dimen.button_horizontalpadding),
                            getResources().getDimensionPixelSize(R.dimen.button_verticalpadding));
                }

                i++;
                viewChild = this.contentView.findViewWithTag(tag + i);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

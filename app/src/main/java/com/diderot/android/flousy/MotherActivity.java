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
import flousy.gui.activitybar.ActivityBarFactory;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.color.ColorBrightness;
import flousy.gui.content.IColorCustomizer;
import flousy.gui.content.IDimensionCustomizer;
import flousy.gui.content.ITextCustomizer;
import flousy.gui.color.ColorOnTouchListener;
import flousy.gui.content.ListMenu;
import flousy.gui.recycler.drawer.DrawerItemMenu;
import flousy.gui.recycler.drawer.Drawer;
import flousy.gui.recycler.drawer.DrawerItemTitle;
import flousy.tool.Session;

public class MotherActivity extends Activity implements IColorCustomizer, ITextCustomizer, IDimensionCustomizer {

    public static final String EXTRA_CLOSE = "CLOSE";
    public static final String EXTRA_ACTIVITY_COLOR = "ACTIVITY_COLOR";
    public static final String EXTRA_USER_FIRSTNAME = "USER_FIRSTNAME";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String EXTRA_ARTICLE_NAME = "ARTICLE_NAME";

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
        this.actionBar.setSubTitleViewEnabled(false);

        //Create Drawer
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawerView = (RecyclerView) findViewById(R.id.drawer_view);

        this.drawer = new Drawer(this);
        this.drawer.setDrawerLayout(this.drawerLayout);
        this.drawer.adapt(this.drawerView);

        //Add Items menu
        Session session = new Session(this);
        if(session.isUserLogged()) {
            addMenuDrawerItems();
        }

        //Customize activity default
        customizeColor();
        customizeText();
        customizeDimensions();
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.drawer.close();
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

    public View getContentView() {
        return this.contentView;
    }

    @Override
    public void setContentView(int layoutResID) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(layoutResID);
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

    private void addMenuDrawerItems() {
        DrawerItemTitle drawerItemTitle = new DrawerItemTitle();
        drawerItemTitle.setText(getResources().getString(R.string.activity_menu_name));
        this.drawer.addItem(drawerItemTitle);

        ListMenu listMenu = ListMenu.getInstance(this);
        flousy.gui.content.Menu menu;

        DrawerItemMenu drawerItemMenu = null;
        for(int i=0; i<listMenu.count(); i++) {
            drawerItemMenu = new DrawerItemMenu();
            menu = listMenu.get(i);

            drawerItemMenu.setColor(menu.getColor());
            drawerItemMenu.setText(menu.getName());
            drawerItemMenu.setIntent(menu.getIntent());

            this.drawer.addItem(drawerItemMenu);
        }
    }

    public ActivityBar getActivityBar() {
        return this.activityBar;
    }

    public ActivityBar createActivityBar(ActivityBarType type) {
        this.activityBar = ActivityBarFactory.create(type);

        if(this.activityBar == null) {
            return null;
        }

        ViewStub viewStub = (ViewStub) findViewById(R.id.activitybar_viewstub);
        this.activityBar.inflate(viewStub);
        this.activityBar.getView().setBackgroundColor(this.activityColor);

        return this.activityBar;
    }

    @Override
    public void customizeColor() {
        this.actionBar.setColor(this.activityColor);

        ColorOnTouchListener listener = new ColorOnTouchListener(this.activityColor);

        this.actionBar.getLayoutActionUp().setOnTouchListener(listener);
        this.actionBar.getActionFirstButton().setOnTouchListener(listener);
        this.actionBar.getActionSecondButton().setOnTouchListener(listener);
        this.actionBar.getActionDrawerButton().setOnTouchListener(listener);

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
                            getResources().getDimensionPixelSize(R.dimen.activitycontent_padding),
                            0,
                            0,
                            0);
                } else if(viewChild.getClass().getSimpleName().compareTo("Button") == 0) {
                    viewChild.setPadding(
                            getResources().getDimensionPixelSize(R.dimen.button_horizontalpadding),
                            getResources().getDimensionPixelSize(R.dimen.button_verticalpadding),
                            getResources().getDimensionPixelSize(R.dimen.button_horizontalpadding),
                            getResources().getDimensionPixelSize(R.dimen.button_verticalpadding));
                }

                i++;
                viewChild = this.contentView.findViewWithTag(tag + i);
            }
        }
    }
}

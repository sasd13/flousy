package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import flousy.gui.actionbar.ActionBar;
import flousy.gui.content.ListMenu;
import flousy.gui.recycler.grid.GridItem;
import flousy.gui.recycler.grid.Grid;
import flousy.gui.widget.CustomDialogBuilder;
import flousy.tool.Session;

public class MenuActivity extends MotherActivity {

    private static int LOGOUT_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    private Grid gridMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityContent
        setContentView(R.layout.recyclerview);

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(R.string.activity_menu_name);
        actionBar.setActionUpButtonEnabled(false);

        ImageButton buttonLogout = actionBar.getActionFirstButton();
        buttonLogout.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_logout));
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogBuilder builder = new CustomDialogBuilder(MenuActivity.this, CustomDialogBuilder.TYPE_TWOBUTTON_YESNO);
                builder.setTitle(R.string.settings_alertdialog_logout_title)
                        .setMessage(R.string.alertdialog_confirm_message)
                        .setPositiveButton(new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                endConnection();
                            }
                        })
                        .setNegativeButton(null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        actionBar.setActionFirstButtonEnabled(true);

        //Disable Drawer
        actionBar.setActionDrawerButtonEnabled(false);
        getDrawer().setEnabled(false);

        //Set Activity content
        RecyclerView gridView = (RecyclerView) findViewById(R.id.recyclerview);
        this.gridMenu = new Grid(this);
        this.gridMenu.adapt(gridView);

        //Add items
        ListMenu listMenu = ListMenu.getInstance(this);
        flousy.gui.content.Menu menu;

        GridItem gridItem;
        for(int i=0; i<listMenu.count(); i++) {
            gridItem = new GridItem();
            menu = listMenu.get(i);

            gridItem.setColor(menu.getColor());
            gridItem.setImage(menu.getImage());
            gridItem.setText(menu.getName());
            gridItem.setIntent(menu.getIntent());

            this.gridMenu.addItem(gridItem);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra(EXTRA_WELCOME) && getIntent().getBooleanExtra(EXTRA_WELCOME, false) == true) {
            getIntent().removeExtra(EXTRA_WELCOME);

            CharSequence firstName = getIntent().getCharSequenceExtra(EXTRA_USER_FIRSTNAME);

            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.menu_alertdialog_welcome_title)
                    .setMessage(getResources().getString(R.string.menu_alertdialog_welcome_message) + " " + firstName + " !")
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (getIntent().hasExtra(EXTRA_EXIT) && getIntent().getBooleanExtra(EXTRA_EXIT, false) == true) {
            getIntent().removeExtra(EXTRA_EXIT);

            Intent intent = new Intent(this, LogInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
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
        return super.onOptionsItemSelected(item);
    }

    public void endConnection() {
        Session session = new Session(this);

        boolean deconnected = session.logOut();
        if(deconnected == false) {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.alertdialog_title_error)
                    .setMessage(R.string.settings_alertdialog_lougout_message_error)
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
            final AlertDialog dialog = builder.create();

            final Intent intent = new Intent(this, LogInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

            this.runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(intent);
                    dialog.dismiss();
                    finish();
                }
            };

            this.handler = new Handler();
            this.handler.postDelayed(this.runnable, LOGOUT_TIME_OUT);

            dialog.show();
        }
    }
}
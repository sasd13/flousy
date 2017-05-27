package com.sasd13.flousy.controller;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sasd13.flousy.activities.MainActivity;
import com.sasd13.flousy.view.fragment.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class Controller implements IController {

    protected MainActivity mainActivity;
    private View contentView;

    protected Controller(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        contentView = mainActivity.findViewById(android.R.id.content);
    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    protected void startFragment(Fragment fragment) {
        mainActivity.startFragment(fragment);
    }

    public void backPress() {
        mainActivity.onBackPressed();
    }
}

package com.sasd13.flousy.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.sasd13.flousy.util.EnumError;
import com.sasd13.flousy.view.IController;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class Controller implements IController {

    private Activity activity;
    private View contentView;

    protected Controller(Activity activity) {
        this.activity = activity;
        contentView = activity.findViewById(android.R.id.content);
    }

    protected Activity getActivity() {
        return activity;
    }

    protected void startActivity(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getActivity().startActivity(intent);
        activity.finish();
    }

    @Override
    public void display(@StringRes int resID) {
        Snackbar.make(contentView, resID, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void display(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    public void onFail(Map<String, String> errors) {
        if (getScope() != null && getScope().isLoading()) {
            getScope().setLoading(false);
        }

        if (errors != null && !errors.isEmpty()) {
            Iterator<String> it = errors.keySet().iterator();
            EnumError error = EnumError.find(Integer.valueOf(it.next()));

            display(error.getResID());
        } else {
            display(EnumError.UNKNOWN.getResID());
        }
    }

    public void onCancelled() {
        if (getScope() != null) {
            if (getScope().isLoading()) {
                getScope().setLoading(false);
            }

            getScope().setCancelled(true);
        }
    }
}

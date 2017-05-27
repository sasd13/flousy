package com.sasd13.flousy.controller;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.sasd13.javaex.net.EnumHttpStatus;
import com.sasd13.proadmin.android.util.EnumErrorRes;
import com.sasd13.proadmin.android.view.IController;
import com.sasd13.proadmin.util.EnumError;

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

    public Activity getActivity() {
        return activity;
    }

    @Override
    public void display(@StringRes int resID) {
        Snackbar.make(contentView, resID, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void display(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    public void onFail(int httpStatus, Map<String, String> errors) {
        if (getScope().isLoading()) {
            getScope().setLoading(false);
        }

        if (errors != null && !errors.isEmpty()) {
            Iterator<String> it = errors.keySet().iterator();
            EnumError error = EnumError.find(Integer.valueOf(it.next()));

            display(EnumErrorRes.find(error).getResID());
        } else {
            EnumHttpStatus status = EnumHttpStatus.find(httpStatus);

            if (status != null) {
                display(status.getDesc());
            } else {
                display(EnumErrorRes.UNKNOWN.getResID());
            }
        }
    }

    public void onCancelled() {
        if (getScope().isLoading()) {
            getScope().setLoading(false);
        }

        getScope().setCancelled(true);
    }
}

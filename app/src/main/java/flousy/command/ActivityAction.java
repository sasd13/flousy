package flousy.command;

import android.app.Activity;

/**
 * Created by Samir on 07/11/2015.
 */
public abstract class ActivityAction implements Action {

    protected Activity activity;

    public ActivityAction(Activity activity) {
        this.activity = activity;
    }
}

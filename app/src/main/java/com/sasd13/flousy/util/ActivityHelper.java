package com.sasd13.flousy.util;

import android.app.Activity;

/**
 * Created by Samir on 06/03/2016.
 */
public class ActivityHelper {

    public static long getCurrentExtraId(Activity activity, String extraKey) {
        long currentExtraId = activity.getIntent().getLongExtra(extraKey, 0);

        if (currentExtraId == 0) {
            currentExtraId = SessionHelper.getExtraIdFromSession(extraKey);
        } else {
            SessionHelper.setExtraIdInSession(extraKey, currentExtraId);
        }

        return currentExtraId;
    }
}

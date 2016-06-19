package com.sasd13.flousy.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.HomeActivity;
import com.sasd13.flousy.constant.Extra;

/**
 * Created by Samir on 06/03/2016.
 */
public class ActivityHelper {

    private static final int TIMEOUT_EXIT = 2000;

    public static void goToActivity(Context context, Class<?> mClass) {
        goToActivity(context, mClass, 0);
    }

    public static void goToActivity(final Context context, final Class<?> mClass, int timeOut) {
        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, mClass);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                context.startActivity(intent);
            }
        }, timeOut);
    }

    public static void goToHomeActivityAndExit(final Activity activity) {
        final WaitDialog waitDialog = new WaitDialog(activity);

        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Extra.EXIT, true);

                activity.startActivity(intent);
                waitDialog.dismiss();
                activity.finish();
            }
        }, TIMEOUT_EXIT);

        taskPlanner.start();
        waitDialog.show();
    }

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

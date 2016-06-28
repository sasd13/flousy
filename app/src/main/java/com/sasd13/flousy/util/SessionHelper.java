package com.sasd13.flousy.util;

import android.app.Activity;
import android.content.Intent;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.HomeActivity;
import com.sasd13.flousy.LoginActivity;
import com.sasd13.flousy.SignActivity;
import com.sasd13.flousy.constant.Extra;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    private static final int TIMEOUT = 1500;

    public static boolean isLogged() {
        return Session.containsAttribute(Extra.CUSTOMER_ID);
    }

    public static void logIn(Activity activity, long customerId, String firstName) {
        setExtraIdInSession(Extra.CUSTOMER_ID, customerId);

        if (SignActivity.class.equals(activity.getClass())) {
            LoginActivity.self.finish();
            goToHomeActivityWithWelcome((SignActivity) activity, firstName);
        } else {
            goToHomeActivity((LoginActivity) activity);
        }
    }

    private static void goToHomeActivityWithWelcome(final SignActivity signActivity, String firstName) {
        final WaitDialog waitDialog = new WaitDialog(signActivity);
        final Intent intent = new Intent(signActivity, HomeActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.WELCOME, true);
        intent.putExtra(Extra.FIRSTNAME, firstName);

        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                signActivity.startActivity(intent);
                waitDialog.dismiss();
                signActivity.finish();
            }
        }, TIMEOUT);

        taskPlanner.start();
        waitDialog.show();
    }

    private static void goToHomeActivity(final LoginActivity loginActivity) {
        final WaitDialog waitDialog = new WaitDialog(loginActivity);
        final Intent intent = new Intent(loginActivity, HomeActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                loginActivity.startActivity(intent);
                waitDialog.dismiss();
                loginActivity.finish();
            }
        }, TIMEOUT).start();

        waitDialog.show();
    }

    public static void logOut(Activity activity) {
        Session.clear();

        if (HomeActivity.class.equals(activity.getClass())) {
            HomeActivity.self.exit();
        } else {
            Intent intent = new Intent(activity, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(Extra.EXIT, true);

            activity.startActivity(intent);
        }
    }

    public static long getExtraIdFromSession(String extraKey) {
        return Long.parseLong(Session.getAttribute(extraKey));
    }

    public static void setExtraIdInSession(String extraKey, long id) {
        Session.setAttribute(extraKey, String.valueOf(id));
    }

    public static long getCurrentExtraId(Activity activity, String extraKey) {
        long currentExtraId = activity.getIntent().getLongExtra(extraKey, 0);

        if (currentExtraId == 0) {
            currentExtraId = getExtraIdFromSession(extraKey);
        } else {
            setExtraIdInSession(extraKey, currentExtraId);
        }

        return currentExtraId;
    }
}

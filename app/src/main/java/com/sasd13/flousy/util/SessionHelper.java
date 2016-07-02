package com.sasd13.flousy.util;

import android.app.Activity;
import android.content.Intent;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.HomeActivity;
import com.sasd13.flousy.LoginActivity;
import com.sasd13.flousy.SignActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.Extra;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    private static final int TIMEOUT = 1500;

    public static boolean isLogged() {
        return Session.containsAttribute(Extra.CUSTOMER_ID);
    }

    public static void logIn(final Activity activity, final Customer customer) {
        setExtraIdInSession(Extra.CUSTOMER_ID, customer.getId());

        final WaitDialog waitDialog = new WaitDialog(activity);
        final Intent intent = new Intent(activity, HomeActivity.class);

        waitDialog.setCancelable(false);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                if (SignActivity.class.equals(activity.getClass())) {
                    LoginActivity.self.finish();

                    intent.putExtra(Extra.WELCOME, true);
                    intent.putExtra(Extra.FIRSTNAME, customer.getFirstName());
                }

                activity.startActivity(intent);
                waitDialog.dismiss();
                activity.finish();
            }
        }, TIMEOUT).start();

        waitDialog.show();
    }

    public static void logOut(Activity activity) {
        Session.clear();

        if (HomeActivity.class.equals(activity.getClass())) {
            HomeActivity.self.exit();
        } else {
            Intent intent = HomeActivity.self.getIntent();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(Extra.EXIT, true);

            activity.startActivity(intent);
            activity.finish();
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

package com.sasd13.flousy.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.activities.HomeActivity;
import com.sasd13.flousy.activities.LogInActivity;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activities.SignActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.Extra;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    public static boolean isLogged(Context context) {
        return Session.containsAttribute(context, Extra.CUSTOMER_ID);
    }

    public static void logIn(final Activity activity, final Customer customer) {
        setExtraId(activity, Extra.CUSTOMER_ID, customer.getId());

        final WaitDialog waitDialog = new WaitDialog(activity);
        final Intent intent = new Intent(activity, HomeActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                if (SignActivity.class.equals(activity.getClass())) {
                    LogInActivity.self.finish();
                    LogInActivity.self = null;

                    intent.putExtra(Extra.WELCOME, true);
                    intent.putExtra(Extra.FIRSTNAME, customer.getFirstName());
                }

                activity.startActivity(intent);
                waitDialog.dismiss();
                activity.finish();
            }
        }, GUIConstants.TIMEOUT_ACTIVITY).start();

        waitDialog.show();
    }

    public static void logOut(final Activity activity) {
        OptionDialog.showOkCancelDialog(
                activity,
                activity.getResources().getString(R.string.button_logout),
                activity.getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        exit(activity);
                    }
                }
        );
    }

    private static void exit(Activity activity) {
        Session.clear(activity);

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

    public static long getExtraId(Context context, String extraKey) {
        return Long.parseLong(Session.getAttribute(context, extraKey));
    }

    public static void setExtraId(Context context, String extraKey, long id) {
        Session.setAttribute(context, extraKey, String.valueOf(id));
    }

    public static long getIntentExtraId(Activity activity, String extraKey) {
        long intentExtraId = activity.getIntent().getLongExtra(extraKey, 0);

        if (intentExtraId == 0) {
            intentExtraId = getExtraId(activity, extraKey);
        } else {
            setExtraId(activity, extraKey, intentExtraId);
        }

        return intentExtraId;
    }
}

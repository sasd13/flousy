package com.sasd13.flousy.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sasd13.androidex.util.Session;
import com.sasd13.flousy.HomeActivity;
import com.sasd13.flousy.constant.Extra;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    public static boolean isLogged() {
        return Session.containsAttribute(Extra.CUSTOMER_ID);
    }

    public static void logIn(long customerId) {
        setExtraIdInSession(Extra.CUSTOMER_ID, customerId);
    }

    public static void logOut(Context context) {
        Session.clear();

        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.EXIT, true);

        context.startActivity(intent);
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

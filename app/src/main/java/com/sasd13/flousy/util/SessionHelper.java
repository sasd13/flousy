package com.sasd13.flousy.util;

import android.app.Activity;

import com.sasd13.androidex.util.Session;
import com.sasd13.flousy.constant.Extra;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    public static boolean isLogged() {
        return Session.containsAttribute(Extra.CUSTOMER_ID);
    }

    public static void logIn(long customerId) {
        SessionHelper.setExtraIdInSession(Extra.CUSTOMER_ID, customerId);
    }

    public static void logOut(Activity activity) {
        Session.clear();
        ActivityHelper.goToHomeActivityAndExit(activity);
    }

    public static long getExtraIdFromSession(String extraKey) {
        return Long.parseLong(Session.getAttribute(extraKey));
    }

    public static void setExtraIdInSession(String extraKey, long id) {
        Session.setAttribute(extraKey, String.valueOf(id));
    }
}

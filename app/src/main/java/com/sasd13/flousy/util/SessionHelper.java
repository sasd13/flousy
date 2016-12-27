package com.sasd13.flousy.util;

import android.content.Context;

import com.sasd13.androidex.util.Session;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    public static boolean isLogged(Context context) {
        return Session.containsAttribute(context, Extra.ID_CUSTOMER_EMAIL);
    }

    public static String getExtraId(Context context, String extraKey) {
        return Session.getAttribute(context, extraKey);
    }

    public static void setExtraId(Context context, String extraKey, String id) {
        Session.setAttribute(context, extraKey, String.valueOf(id));
    }

    public static String getExtraIdTeacherNumber(Context context) {
        return Session.getAttribute(context, Extra.ID_CUSTOMER_EMAIL);
    }

    public static void clear(Context context) {
        Session.clear(context);
    }
}

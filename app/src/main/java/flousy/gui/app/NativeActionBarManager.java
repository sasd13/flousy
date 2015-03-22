package flousy.gui.app;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

/**
 * Created by Samir on 22/03/2015.
 */
public class NativeActionBarManager {

    public static void setActionBarEnabled(ActionBarActivity activity, boolean enabled) {
        if(enabled == true) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                try {
                    activity.getActionBar().show();
                } catch (Exception e) {
                    Log.println(1, null, "ActionBar is null");
                }
            } else {
                activity.getSupportActionBar().show();
            }
        } else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                try {
                    activity.getActionBar().hide();
                } catch (Exception e) {
                    Log.println(1, null, "ActionBar is null");
                }
            } else {
                activity.getSupportActionBar().hide();
            }
        }
    }
}

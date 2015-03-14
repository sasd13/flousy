package flousy.util.activitybar;

import android.content.Context;

/**
 * Created by Samir on 11/03/2015.
 */
public class ActivityBarFactory {

    public static final int TYPE_BASEACTIVITYBAR = 0;
    public static final int TYPE_TITLEDACTIVITYBAR = 1;

    private ActivityBarFactory() {}

    public static ActivityBar create(int type) {
        ActivityBar activityBar = null;

        switch (type) {
            case TYPE_BASEACTIVITYBAR :
                activityBar = new BaseActivityBar();
                break;
            case TYPE_TITLEDACTIVITYBAR :
                activityBar = new TitledActivityBar();
                break;
        }

        return activityBar;
    }
}

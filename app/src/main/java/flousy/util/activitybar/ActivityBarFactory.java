package flousy.util.activitybar;

/**
 * Created by Samir on 11/03/2015.
 */
public class ActivityBarFactory {

    public static final int TYPE_SIMPLEACTIVITYBAR = 0;
    public static final int TYPE_TITLEDACTIVITYBAR = 1;

    public ActivityBarFactory() { }

    public ActivityBar createActivityBar(int barType) {
        ActivityBar activityBar = null;

        switch (barType) {
            case TYPE_SIMPLEACTIVITYBAR :
                activityBar = new SimpleActivityBar();
                break;
            case TYPE_TITLEDACTIVITYBAR :
                activityBar = new TitledActivityBar();
                break;
        }

        return activityBar;
    }
}

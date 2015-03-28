package flousy.gui.activitybar;

/**
 * Created by Samir on 11/03/2015.
 */
public class ActivityBarFactory {

    private ActivityBarFactory() {}

    public static ActivityBar create(ActivityBarType type) {
        switch (type) {
            case TITLEDBAR :
                return new TitledActivityBar();
        }

        return null;
    }
}

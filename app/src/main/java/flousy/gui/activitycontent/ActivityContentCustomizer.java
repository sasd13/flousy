package flousy.gui.activitycontent;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.diderot.android.flousy.MotherActivity;
import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class ActivityContentCustomizer {

    public static void customizeColor(View contentView, Activity activity) {
        if(contentView != null) {
            if (activity instanceof MotherActivity) {
                customizeColor(activity, contentView, ((MotherActivity) activity).getActivityColor());
            } else {
                customizeColor(activity, contentView, activity.getResources().getColor(MotherActivity.APP_COLOR));
            }
        }
    }

    private static void customizeColor(Activity activity, View view, int activityColor) {
        int i = 0;
        String tag = "color_activity_";
        View viewChild = view.findViewWithTag(tag + i);
        while (viewChild != null) {
            if(viewChild.getClass().getSimpleName().compareTo("TextView") == 0) {
                ((TextView) viewChild).setTextColor(activityColor);
                CharSequence text = ((TextView) viewChild).getText();
                ((TextView) viewChild).setText(text.toString().toUpperCase());
            } else if(viewChild.getClass().getSimpleName().compareTo("Button") == 0) {
                ((Button) viewChild).setTextColor(activity.getResources().getColor(R.color.white));
                viewChild.setBackgroundColor(activityColor);
            } else {
                viewChild.setBackgroundColor(activityColor);
            }

            i++;
            viewChild = view.findViewWithTag(tag + i);
        }
    }
}

package flousy.gui.activitycontent;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.diderot.android.flousy.MotherActivity;
import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class ActivityContentCustomizer {

    public static void customize(Activity activity, View view) {
        int activityColor = 0;

        if (activity instanceof MotherActivity) {
            activityColor = ((MotherActivity) activity).getActivityColor();
        } else {
            activityColor =  activity.getResources().getColor(MotherActivity.APP_COLOR);
        }

        Resources resources = activity.getResources();

        int i = 0;
        String tag = "color_activity_";
        View viewChild = view.findViewWithTag(tag + i);
        while (viewChild != null) {
            if(viewChild.getClass().getSimpleName().compareTo("TextView") == 0) {
                CharSequence text = ((TextView) viewChild).getText();
                ((TextView) viewChild).setText(text.toString().toUpperCase());
                ((TextView) viewChild).setTypeface(Typeface.DEFAULT_BOLD);
                ((TextView) viewChild).setTextColor(activityColor);
                viewChild.setPadding(
                        (int) resources.getDimension(R.dimen.activitycontent_padding),
                        0,
                        0,
                        0);
            } else if(viewChild.getClass().getSimpleName().compareTo("Button") == 0) {
                ((Button) viewChild).setTextSize(TypedValue.COMPLEX_UNIT_DIP, resources.getDimension(R.dimen.textsize_medium));
                ((Button) viewChild).setTypeface(Typeface.DEFAULT_BOLD);
                ((Button) viewChild).setTextColor(resources.getColor(R.color.white));
                viewChild.setBackgroundColor(activityColor);
                viewChild.setPadding(
                        (int) resources.getDimension(R.dimen.button_horizontalpadding),
                        (int) resources.getDimension(R.dimen.button_verticalpadding),
                        (int) resources.getDimension(R.dimen.button_horizontalpadding),
                        (int) resources.getDimension(R.dimen.button_verticalpadding));
            } else {
                viewChild.setBackgroundColor(activityColor);
            }

            i++;
            viewChild = view.findViewWithTag(tag + i);
        }
    }
}

package flousy.gui.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.View;

import com.diderot.android.flousy.MotherActivity;

import flousy.gui.listener.CustomOnTouchListener;

/**
 * Created by Samir on 22/03/2015.
 */
public class ActionBarCustomizer {

    public static void customize(AbstractActionBar actionBar, Activity activity) {
        if(actionBar != null) {
            if(actionBar.getClass().getSimpleName().compareTo("BaseActionBar") == 0) {
                base((BaseActionBar) actionBar, activity);
            }
        }
    }

    private static void base(BaseActionBar actionBar, final Activity activity) {
        int activityColor;

        if(activity instanceof MotherActivity) {
            activityColor = ((MotherActivity) activity).getActivityColor();
        } else {
            activityColor = activity.getResources().getColor(MotherActivity.APP_COLOR);
        }

        actionBar.getView().setBackgroundColor(activityColor);
        actionBar.setPreviousEnabled(true);
        actionBar.setSubTitleEnabled(false);
        actionBar.setActionFirstEnabled(false);
        actionBar.setActionSecondEnabled(false);

        CustomOnTouchListener listener = new CustomOnTouchListener(activityColor);
        actionBar.getImageButtonPrevious().setOnTouchListener(listener);
        actionBar.getImageButtonDrawer().setOnTouchListener(listener);
        actionBar.getImageButtonActionFirst().setOnTouchListener(listener);
        actionBar.getImageButtonActionSecond().setOnTouchListener(listener);

        actionBar.getImageButtonPrevious().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }
}

package flousy.gui.actionbar;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.View;

import com.diderot.android.flousy.MotherActivity;

import flousy.gui.listener.CustomOnTouchListener;

/**
 * Created by Samir on 25/03/2015.
 */
public class ActionBarCustomizer {

    private Activity activity;
    private ActionBar actionBar;

    public static void customize(final Activity activity, ActionBar actionBar) {
        int activityColor;

        if(activity instanceof MotherActivity) {
            activityColor = ((MotherActivity) activity).getActivityColor();
        } else {
            activityColor = activity.getResources().getColor(MotherActivity.APP_COLOR);
        }

        actionBar.setBackgroundColor(activityColor);
        actionBar.setUpEnabled(true);
        actionBar.setSubTitleEnabled(false);
        actionBar.setActionFirstEnabled(false);
        actionBar.setActionSecondEnabled(false);
        actionBar.setDrawerEnabled(true);

        actionBar.getUpButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upIntent = NavUtils.getParentActivityIntent(activity);
                if (NavUtils.shouldUpRecreateTask(activity, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(activity)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                                    // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(activity, upIntent);
                }
            }
        });

        CustomOnTouchListener listener = new CustomOnTouchListener(activityColor);
        actionBar.getUpButton().setOnTouchListener(listener);
        actionBar.getActionFirstButton().setOnTouchListener(listener);
        actionBar.getActionSecondButton().setOnTouchListener(listener);
        actionBar.getDrawerButton().setOnTouchListener(listener);
    }
}

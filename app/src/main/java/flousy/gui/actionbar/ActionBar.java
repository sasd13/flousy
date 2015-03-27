package flousy.gui.actionbar;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.TextView;

import com.diderot.android.flousy.MotherActivity;
import com.diderot.android.flousy.R;

import flousy.gui.listener.CustomOnTouchListener;

/**
 * Created by Samir on 19/03/2015.
 */
public class ActionBar {

    private View view;
    private int backgroundColor;

    private ImageButton upButton, actionFirstButton, actionSecondButton, drawerButton;
    private TextView titleView, subTitleView;

    public ActionBar() {
        this.backgroundColor = 0;
        this.view = null;

        this.upButton = null;
        this.drawerButton = null;
        this.titleView = null;
        this.subTitleView = null;
        this.actionFirstButton = null;
        this.actionSecondButton = null;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;

        if(this.view != null) {
            this.view.setBackgroundColor(this.backgroundColor);
        }
    }

    public ImageButton getUpButton() {
        setUpEnabled(true);

        return this.upButton;
    }

    public void setUpEnabled(boolean enabled) {
        if(this.upButton != null) {
            this.upButton.setEnabled(enabled);
            if(enabled == true) {
                this.upButton.setVisibility(View.VISIBLE);
            } else {
                this.upButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    public TextView getTitleView() {
        return this.titleView;
    }

    public TextView getSubTitleView() {
        setSubTitleEnabled(true);

        return this.subTitleView;
    }

    public void setSubTitleEnabled(boolean enabled) {
        if(this.subTitleView != null) {
            if(enabled == true) {
                ViewGroup.LayoutParams params = this.subTitleView.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                this.subTitleView.setLayoutParams(params);
            } else {
                ViewGroup.LayoutParams params = this.subTitleView.getLayoutParams();
                params.height = 0;
                this.subTitleView.setLayoutParams(params);
            }
        }
    }

    public ImageButton getActionFirstButton() {
        setActionFirstEnabled(true);

        return this.actionFirstButton;
    }

    public void setActionFirstEnabled(boolean enabled) {
        if(this.actionFirstButton != null) {
            this.actionFirstButton.setEnabled(enabled);
            if(enabled == true) {
                this.actionFirstButton.setVisibility(View.VISIBLE);
            } else {
                this.actionFirstButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    public ImageButton getActionSecondButton() {
        setActionSecondEnabled(true);

        return this.actionSecondButton;
    }

    public void setActionSecondEnabled(boolean enabled) {
        if(this.actionSecondButton != null) {
            this.actionSecondButton.setEnabled(enabled);
            if(enabled == true) {
                this.actionSecondButton.setVisibility(View.VISIBLE);
            } else {
                this.actionSecondButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    public ImageButton getDrawerButton() {
        setDrawerEnabled(true);

        return this.drawerButton;
    }

    public void setDrawerEnabled(boolean enabled) {
        if(this.drawerButton != null) {
            this.drawerButton.setEnabled(enabled);
            if(enabled == true) {
                this.drawerButton.setVisibility(View.VISIBLE);
            } else {
                this.drawerButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(R.layout.actionbar);
        this.view = viewStub.inflate();

        this.backgroundColor = viewStub.getContext().getResources().getColor(MotherActivity.APP_COLOR);
        this.view.setBackgroundColor(this.backgroundColor);

        this.upButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_up);
        this.titleView = (TextView) this.view.findViewById(R.id.actionbar_textview_title);
        this.subTitleView = (TextView) this.view.findViewById(R.id.actionbar_textview_subtitle);
        this.actionFirstButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_action_first);
        this.actionSecondButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_action_second);
        this.drawerButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_drawer);
    }

    public ActionBar customize(final Activity activity) {
        int activityColor;
        if(activity instanceof MotherActivity) {
            activityColor = ((MotherActivity) activity).getActivityColor();
        } else {
            activityColor = activity.getResources().getColor(MotherActivity.APP_COLOR);
        }

        setBackgroundColor(activityColor);
        setUpEnabled(true);
        setSubTitleEnabled(false);
        setActionFirstEnabled(false);
        setActionSecondEnabled(false);
        setDrawerEnabled(true);

        this.upButton.setOnClickListener(new View.OnClickListener() {
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
        this.upButton.setOnTouchListener(listener);
        this.actionFirstButton.setOnTouchListener(listener);
        this.actionSecondButton.setOnTouchListener(listener);
        this.drawerButton.setOnTouchListener(listener);

        return this;
    }
}

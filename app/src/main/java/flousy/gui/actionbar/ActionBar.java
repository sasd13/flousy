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

/**
 * Created by Samir on 19/03/2015.
 */
public class ActionBar {

    private Activity activity;
    private int color;
    private View view;

    private ImageButton actionUpButton, actionFirstButton, actionSecondButton, actionDrawerButton;
    private TextView titleView, subTitleView;

    public ActionBar(Activity activity) {
        this.activity = activity;
        this.color = 0;
        this.view = null;

        this.actionUpButton = null;
        this.actionDrawerButton = null;
        this.titleView = null;
        this.subTitleView = null;
        this.actionFirstButton = null;
        this.actionSecondButton = null;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;

        if(this.view != null) {
            this.view.setBackgroundColor(this.color);
        }
    }

    public ImageButton getActionUpButton() {
        return this.actionUpButton;
    }

    public void setActionUpButtonEnabled(boolean enabled) {
        if(enabled) {
            this.actionUpButton.setVisibility(View.VISIBLE);
        } else {
            this.actionUpButton.setVisibility(View.INVISIBLE);
        }
    }

    public TextView getTitleView() {
        return this.titleView;
    }

    public TextView getSubTitleView() {
        return this.subTitleView;
    }

    public void setSubTitleViewEnabled(boolean enabled) {
        ViewGroup.LayoutParams params = this.subTitleView.getLayoutParams();

        if(enabled) {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            params.height = 0;
        }

        this.subTitleView.setLayoutParams(params);
    }

    public ImageButton getActionFirstButton() {
        return this.actionFirstButton;
    }

    public void setActionFirstButtonEnabled(boolean enabled) {
        if(enabled) {
            this.actionFirstButton.setVisibility(View.VISIBLE);
        } else {
            this.actionFirstButton.setVisibility(View.INVISIBLE);
        }
    }

    public ImageButton getActionSecondButton() {
        return this.actionSecondButton;
    }

    public void setActionSecondButtonEnabled(boolean enabled) {
        if(enabled) {
            this.actionSecondButton.setVisibility(View.VISIBLE);
        } else {
            this.actionSecondButton.setVisibility(View.INVISIBLE);
        }
    }

    public ImageButton getActionDrawerButton() {
        return this.actionDrawerButton;
    }

    public void setActionDrawerButtonEnabled(boolean enabled) {
        if(enabled) {
            this.actionDrawerButton.setVisibility(View.VISIBLE);
        } else {
            this.actionDrawerButton.setVisibility(View.INVISIBLE);
        }
    }

    public void inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(R.layout.actionbar);
        this.view = viewStub.inflate();

        this.color = this.view.getContext().getResources().getColor(MotherActivity.APP_COLOR);
        this.view.setBackgroundColor(this.color);

        this.actionUpButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_actionup);

        this.titleView = (TextView) this.view.findViewById(R.id.actionbar_textview_title);

        this.subTitleView = (TextView) this.view.findViewById(R.id.actionbar_textview_subtitle);
        setSubTitleViewEnabled(false);

        this.actionFirstButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_actionfirst);
        this.actionSecondButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_actionsecond);
        this.actionDrawerButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_actiondrawer);

        setNavigationUp();
    }

    public void show() {
        ViewGroup.LayoutParams params = this.view.getLayoutParams();
        params.height = this.view.getContext().getResources().getDimensionPixelSize(R.dimen.actionbar_height);
        this.view.setLayoutParams(params);
    }

    public void hide() {
        ViewGroup.LayoutParams params = this.view.getLayoutParams();
        params.height = 0;
        this.view.setLayoutParams(params);
    }

    private void setNavigationUp() {
        this.actionUpButton.setOnClickListener(new View.OnClickListener() {
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
    }
}

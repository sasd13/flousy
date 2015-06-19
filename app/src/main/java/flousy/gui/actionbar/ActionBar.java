package flousy.gui.actionbar;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diderot.android.flousy.MotherActivity;
import com.diderot.android.flousy.R;

import flousy.gui.widget.recycler.drawer.Drawer;

/**
 * Created by Samir on 19/03/2015.
 */
public class ActionBar {

    private MotherActivity activity;
    private int color;
    private View view;

    private RelativeLayout layoutActionUp;
    private ImageView actionUpView;
    private TextView titleView, subTitleView;
    private ImageButton actionFirstButton, actionSecondButton, actionDrawerButton;

    public ActionBar(MotherActivity activity) {
        this.activity = activity;
        this.color = 0;
        this.view = null;

        this.layoutActionUp = null;
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

    public RelativeLayout getLayoutActionUp() {
        return this.layoutActionUp;
    }

    public void setActionUpButtonEnabled(boolean enabled) {
        if(enabled) {
            this.actionUpView.setVisibility(View.VISIBLE);
        } else {
            this.actionUpView.setVisibility(View.INVISIBLE);
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

        this.layoutActionUp = (RelativeLayout) this.view.findViewById(R.id.actionbar_relativelayout_actionup);
        this.actionUpView = (ImageView) this.view.findViewById(R.id.actionbar_imageview_actionup);
        setActionUpNavigation();

        this.titleView = (TextView) this.view.findViewById(R.id.actionbar_textview_title);

        this.subTitleView = (TextView) this.view.findViewById(R.id.actionbar_textview_subtitle);
        setSubTitleViewEnabled(false);

        this.actionFirstButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_actionfirst);
        setActionFirstButtonEnabled(false);

        this.actionSecondButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_actionsecond);
        setActionSecondButtonEnabled(false);

        this.actionDrawerButton = (ImageButton) this.view.findViewById(R.id.actionbar_imagebutton_actiondrawer);
        setActionDrawerListener();
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

    private void setActionUpNavigation() {
        this.layoutActionUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    private void setActionDrawerListener() {
        this.actionDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawer drawer = activity.getDrawer();
                if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            }
        });
    }
}

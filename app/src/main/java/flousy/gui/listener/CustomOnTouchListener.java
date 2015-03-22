package flousy.gui.listener;

import android.view.MotionEvent;
import android.view.View;

import flousy.gui.color.CustomColor;

/**
 * Created by Samir on 12/03/2015.
 */
public class CustomOnTouchListener implements View.OnTouchListener {

    private int customColor;

    public CustomOnTouchListener(int customColor) {
        this.customColor = customColor;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(CustomColor.colorDarker(this.customColor));
            return true;
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            view.setBackgroundColor(this.customColor);
            view.performClick();
            return true;
        }

        return false;
    }
}
package flousy.util.widget;

import android.view.MotionEvent;
import android.view.View;

import flousy.util.color.CustomColor;

/**
 * Created by Samir on 12/03/2015.
 */
public class CustomOnTouchListener implements View.OnTouchListener {

    private CustomColor customColor;

    public CustomOnTouchListener(CustomColor customColor) {
        this.customColor = customColor;
    }

    public CustomColor getCustomColor() {
        return this.customColor;
    }

    public void setCustomColor(CustomColor customColor) {
        this.customColor = customColor;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(this.customColor.getColorDark());
            return true;
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            view.setBackgroundColor(this.customColor.getColor());
            view.performClick();
            return true;
        }

        return false;
    }
}

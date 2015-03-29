package flousy.gui.color;

import android.view.MotionEvent;
import android.view.View;

import flousy.gui.color.ColorBrightness;

/**
 * Created by Samir on 12/03/2015.
 */
public class ColorOnTouchListener implements View.OnTouchListener {

    private int color;

    public ColorOnTouchListener(int color) {
        this.color = color;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(ColorBrightness.colorDarker(this.color));
            return true;
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            view.setBackgroundColor(this.color);
            view.performClick();
            return true;
        } else if(event.getAction() == MotionEvent.ACTION_CANCEL) {
            view.setBackgroundColor(this.color);
            return true;
        }

        return false;
    }
}

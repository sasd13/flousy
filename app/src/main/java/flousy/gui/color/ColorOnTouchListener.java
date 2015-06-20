package flousy.gui.color;

import android.view.MotionEvent;
import android.view.View;

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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                view.setBackgroundColor(ColorBrightness.colorDarker(this.color));
                break;
            case MotionEvent.ACTION_UP:
                view.setBackgroundColor(this.color);
                view.performClick();
                break;
            case MotionEvent.ACTION_CANCEL:
                view.setBackgroundColor(this.color);
                break;
            default:
                return false;
        }

        return true;
    }
}

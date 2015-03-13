package flousy.util.color;

import com.diderot.android.flousy.MyActivity;
import com.diderot.android.flousy.R;

/**
 * Created by Samir on 11/03/2015.
 */
public class CustomColor {

    private int color;
    private int colorLight;

    public CustomColor() {
        this.color = MyActivity.APP_COLOR;
        this.setColorDark(this.color);
    }

    public CustomColor(int color) {
        this.color = color;
        this.setColorDark(this.color);
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
        this.setColorDark(color);
    }

    public int getColorDark() {
        return this.colorLight;
    }

    private void setColorDark(int color) {
        this.colorLight = colorDarker(color);
    }

    private int colorDarker(int color) {
        float[] hsv = new float[3];
        android.graphics.Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f; // value component
        return android.graphics.Color.HSVToColor(hsv);
    }
}

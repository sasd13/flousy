package flousy.gui.color;

import com.diderot.android.flousy.MotherActivity;

/**
 * Created by Samir on 11/03/2015.
 */
public class CustomColor {

    private CustomColor() {}

    public static int colorDarker(int color) {
        float[] hsv = new float[3];
        android.graphics.Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f; // value component
        return android.graphics.Color.HSVToColor(hsv);
    }
}

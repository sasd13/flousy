package flousy.gui.color;

/**
 * Created by Samir on 11/03/2015.
 */
public class ColorBrightness {

    private ColorBrightness() {}

    public static int colorDarker(int color) {
        float[] hsv = new float[3];
        android.graphics.Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f; // value component
        return android.graphics.Color.HSVToColor(hsv);
    }

    public static int colorLighter(int color) {
        float[] hsv = new float[3];
        android.graphics.Color.colorToHSV(color, hsv);
        hsv[2] *= 1.2f; // value component
        return android.graphics.Color.HSVToColor(hsv);
    }
}

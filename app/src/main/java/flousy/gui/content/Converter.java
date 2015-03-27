package flousy.gui.content;

import android.content.Context;

/**
 * Created by Samir on 27/03/2015.
 */
public class Converter {

    public static int dpToPx(Context context, int dimenDP) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dimenDP * scale + 0.5f);
    }
}

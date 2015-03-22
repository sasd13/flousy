package flousy.gui.app;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * Created by Samir on 15/03/2015.
 */
public class KeyboardManager {
    private KeyboardManager() {}

    public static void hide(Context context, TextView textView) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(textView.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}

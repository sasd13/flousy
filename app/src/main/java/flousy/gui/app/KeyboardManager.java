package flousy.gui.app;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * Created by Samir on 15/03/2015.
 */
public class KeyboardManager {

    private KeyboardManager() {}

    public static void hide(TextView textView) {
        InputMethodManager inputMethodManager = (InputMethodManager) textView.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(textView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}

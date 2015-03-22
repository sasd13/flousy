package flousy.gui.actionbar;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewStub;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 19/03/2015.
 */
public class ActionBarFactory {

    private ActionBarFactory() {}

    public static AbstractActionBar create(Context context, ActionBarType type) {
        switch (type) {
            case BASEBAR:
                return new BaseActionBar();
        }

        return null;
    }

    private void customizeActionBar(ActionBar actionBar, ViewStub viewStub) {

    }
}

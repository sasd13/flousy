package flousy.util.activitybar;

import android.view.View;
import android.view.ViewStub;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 11/03/2015.
 */
public class SimpleActivityBar extends ActivityBar {

    public SimpleActivityBar() {
        super.setLayoutResource(R.layout.layout_simpleactivitybar);
    }

    @Override
    public View create(ViewStub viewStub) {
        View view = null;

        viewStub.setLayoutResource(getLayoutResource());
        view = viewStub.inflate();

        return view;
    }
}

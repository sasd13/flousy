package flousy.util.activitybar;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 11/03/2015.
 */
public class BaseActivityBar extends ActivityBar {

    public BaseActivityBar() {
        super(R.layout.layout_baseactivitybar);
    }

    @Override
    public void inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(this.getLayoutResource());

        View view = viewStub.inflate();
        this.setContainerView(view);
    }
}

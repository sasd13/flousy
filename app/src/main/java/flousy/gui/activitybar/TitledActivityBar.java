package flousy.gui.activitybar;

import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 11/03/2015.
 */
public class TitledActivityBar extends ActivityBar {

    private CharSequence title;
    private TextView titleView;

    public TitledActivityBar() {
        super(R.layout.activitybar_titled);

        this.title = "Title";
        this.titleView = null;
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        if(this.titleView != null) {
            this.titleView.setText(this.title);
        }
    }

    @Override
    public View inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(getLayoutResource());
        View view = viewStub.inflate();
        if(view == null) {
            return null;
        }

        setView(view);

        this.titleView = (TextView) view.findViewById(R.id.activitybar_titled_textview);
        this.titleView.setText(this.title);

        return view;
    }
}

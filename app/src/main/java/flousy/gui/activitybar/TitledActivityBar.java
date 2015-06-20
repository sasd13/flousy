package flousy.gui.activitybar;

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
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        try {
            this.titleView.setText(this.title);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(this.layoutResource);
        this.view = viewStub.inflate();

        this.titleView = (TextView) this.view.findViewById(R.id.activitybar_titled_textview);
        this.titleView.setText(this.title);
    }
}

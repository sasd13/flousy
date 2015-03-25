package flousy.gui.drawer;

import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerItemTitle extends AbstractDrawerItem {

    private CharSequence title;
    private TextView titleTextView;

    public DrawerItemTitle(CharSequence itemTitle) {
        super(R.layout.draweritemtitle);

        this.title = itemTitle;
        this.titleTextView = null;
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;
    }

    @Override
    public View inflate(ViewStub viewStub) {
        if(this.titleTextView == null) {
            viewStub.setLayoutResource(getLayoutResource());

            View view = viewStub.inflate();
            setView(view);

            this.titleTextView = (TextView) view.findViewById(R.id.draweritemtitle_textview);
        }

        this.titleTextView.setText(this.title.toString().toUpperCase());

        return getView();
    }
}

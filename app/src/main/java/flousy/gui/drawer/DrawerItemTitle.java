package flousy.gui.drawer;

import android.view.View;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerItemTitle extends AbstractDrawerItem {

    private CharSequence title;
    private TextView titleView;

    public DrawerItemTitle(CharSequence title) {
        super(R.layout.draweritemtitle);

        this.title = title;
        this.titleView = null;
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;
    }

    @Override
    public void parse(View view) {
        if(this.titleView == null) {
            setView(view);
            this.titleView = (TextView) view.findViewById(R.id.draweritemtitle_textview);
        }

        this.titleView.setText(this.title.toString().toUpperCase());
    }
}

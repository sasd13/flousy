package flousy.gui.drawer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class BaseDrawerItemTitle extends DrawerItem {

    private CharSequence title;
    private TextView titleTextView;

    public BaseDrawerItemTitle(Context context, CharSequence itemTitle) {
        super(context, R.layout.draweritemtitle_base);

        this.title = itemTitle;
        this.titleTextView = null;
    }

    public BaseDrawerItemTitle(Context context, CharSequence itemTitle, int layoutResource) {
        super(context, layoutResource);

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
    public void inflate(View view) {
        setView(view);

        ViewGroup viewGroup = (ViewGroup) view;

        this.titleTextView = (TextView) viewGroup.findViewById(R.id.draweritemtitle_textview);
        if(this.titleTextView != null) {
            this.titleTextView.setText(this.title.toString().toUpperCase());
            this.titleTextView.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }
}

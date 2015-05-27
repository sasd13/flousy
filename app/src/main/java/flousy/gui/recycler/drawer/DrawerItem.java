package flousy.gui.recycler.drawer;

import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.diderot.android.flousy.R;

import flousy.gui.recycler.AbstractRecyclerItem;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerItem extends AbstractRecyclerItem {

    private CharSequence text;
    private TextView textView;

    public DrawerItem(int layoutResource) {
        super(layoutResource);

        this.text = "Item";
        this.textView = null;
    }

    public CharSequence getText() {
        return this.text;
    }

    public void setText(CharSequence text) {
        this.text = text;

        try {
            this.textView.setText(this.text);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View inflate(ViewStub viewStub) {
        View view = super.inflate(viewStub);

        this.textView = (TextView) view.findViewById(R.id.draweritem_textview);
        this.textView.setText(this.text);

        return view;
    }
}

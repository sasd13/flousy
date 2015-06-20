package flousy.gui.widget.recycler.drawer;

import android.view.ViewStub;
import android.widget.TextView;

import com.example.flousy.R;

import flousy.gui.widget.recycler.RecyclerItem;

/**
 * <p>
 * Container item class for Navigation drawer :
 * </p>
 * Created by Samir on 22/03/2015.
 */
public class DrawerItem extends RecyclerItem {

    private CharSequence text;
    private TextView textView;

    public DrawerItem(int layoutResource) {
        super(layoutResource);
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
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        this.textView = (TextView) this.view.findViewById(R.id.draweritem_textview);
        this.textView.setText(this.text);
    }
}

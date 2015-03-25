package flousy.gui.drawer;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerItem extends AbstractDrawerItem {

    private CharSequence text;
    private TextView textView;
    private Intent intent;

    public DrawerItem(CharSequence text, Intent intent) {
        super(R.layout.draweritem);

        this.text = text;
        this.textView = null;
        this.intent = intent;
    }

    public DrawerItem(int layoutResource, CharSequence text, Intent intent) {
        super(layoutResource);

        this.text = text;
        this.textView = null;
        this.intent = intent;
    }

    public CharSequence getText() {
        return this.text;
    }

    public void setText(CharSequence text) {
        this.text = text;

        if(this.textView != null) {
            this.textView.setText(this.text);
        }
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    @Override
    public View inflate(ViewStub viewStub) {
        if(this.textView == null) {
            viewStub.setLayoutResource(getLayoutResource());

            View view = viewStub.inflate();
            setView(view);

            this.textView = (TextView) view.findViewById(R.id.draweritem_textview);
        }

        this.textView.setText(this.text);

        return getView();
    }
}

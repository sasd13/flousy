package flousy.gui.drawer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class BaseDrawerItem extends DrawerItem {

    private CharSequence text;
    private TextView textView;
    private Intent intent;

    public BaseDrawerItem(Context context, CharSequence text, Intent intent) {
        super(context, R.layout.draweritem_base);

        this.text = text;
        this.textView = null;
        this.intent = intent;
    }

    public BaseDrawerItem(Context context, CharSequence text, Intent intent, int itemLayoutResource) {
        super(context, itemLayoutResource);

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
    public void inflate(View view) {
        setView(view);

        ViewGroup viewGroup = (ViewGroup) view;

        if(viewGroup != null) {
            this.textView = (TextView) viewGroup.findViewById(R.id.draweritem_base_textview);
            if(this.textView != null) {
                this.textView.setText(this.text);
            }
        }
    }
}

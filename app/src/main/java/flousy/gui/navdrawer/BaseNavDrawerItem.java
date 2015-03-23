package flousy.gui.navdrawer;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class BaseNavDrawerItem extends NavDrawerItem {

    private CharSequence itemText;
    private TextView itemTextView;
    private Intent intent;

    public BaseNavDrawerItem(CharSequence itemText, Intent intent) {
        super(R.layout.navdraweritem_base);

        this.itemText = itemText;
        this.itemTextView = null;
        this.intent = intent;
    }

    public BaseNavDrawerItem(CharSequence itemText, Intent intent, int itemLayoutResource) {
        super(itemLayoutResource);

        this.itemText = itemText;
        this.itemTextView = null;
        this.intent = intent;
    }

    public CharSequence getItemText() {
        return this.itemText;
    }

    public void setItemText(CharSequence itemText) {
        this.itemText = itemText;

        if(this.itemTextView != null) {
            this.itemTextView.setText(this.itemText);
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
            this.itemTextView = (TextView) viewGroup.findViewWithTag("navdraweritem_base_textview");
            if(this.itemTextView != null) {
                this.itemTextView.setText(this.itemText);
            }
        }
    }
}

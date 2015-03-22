package flousy.gui.navdrawer;

import android.content.Context;
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

    public BaseNavDrawerItem(Context context, CharSequence itemText, Intent intent) {
        super(context, R.layout.navdraweritem_base);

        this.itemText = itemText;
        this.itemTextView = null;
        this.intent = intent;
    }

    public BaseNavDrawerItem(Context context, CharSequence itemText, Intent intent, int itemLayoutResource) {
        super(context, itemLayoutResource);

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
    public View inflate() {
        ViewGroup viewGroup = (ViewGroup) getView();

        if(viewGroup != null) {
            this.itemTextView = (TextView) viewGroup.findViewWithTag("navdraweritem_base_textview");
            if(this.itemTextView != null) {
                this.itemTextView.setText(this.itemText);
            }

            viewGroup.setClickable(true);
            viewGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContext().startActivity(getIntent());
                }
            });
        }

        return viewGroup;
    }
}

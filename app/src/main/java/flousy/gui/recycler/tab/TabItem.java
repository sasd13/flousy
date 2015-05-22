package flousy.gui.recycler.tab;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.diderot.android.flousy.R;

import flousy.gui.color.ColorOnTouchListener;
import flousy.gui.recycler.AbstractRecyclerItem;

/**
 * Created by Samir on 22/03/2015.
 */
public class TabItem extends AbstractRecyclerItem {

    private CharSequence nameText, priceText;
    private Intent intent;

    private TextView nameTextView, priceTextView;

    public TabItem() {
        super(R.layout.tabitem);

        this.nameText = "Name";
        this.priceText = "Price";

        this.nameTextView = null;
        this.priceTextView = null;
    }

    public CharSequence getNameText() {
        return this.nameText;
    }

    public void setNameText(CharSequence nameText) {
        this.nameText = nameText;

        if (this.nameTextView != null) {
            this.nameTextView.setText(this.nameText);
        }
    }

    public CharSequence getPriceText() {
        return this.priceText;
    }

    public void setPriceText(CharSequence priceText) {
        this.priceText = priceText;

        if (this.priceTextView != null) {
            this.priceTextView.setText(this.priceText);
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
        viewStub.setLayoutResource(getLayoutResource());
        View view = viewStub.inflate();

        setView(view);

        this.nameTextView = (TextView) view.findViewById(R.id.tabitem_textview_name);
        this.nameTextView.setText(this.nameText);

        this.priceTextView = (TextView) view.findViewById(R.id.tabitem_textview_price);
        this.priceTextView.setText(this.priceText);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent != null) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                }
            }
        });
        int color = view.getContext().getResources().getColor(R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));

        return view;
    }
}

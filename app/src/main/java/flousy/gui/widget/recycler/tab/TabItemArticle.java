package flousy.gui.widget.recycler.tab;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.flousy.R;

import flousy.gui.color.ColorOnTouchListener;
import flousy.gui.widget.recycler.RecyclerItem;

/**
 * <p>
 * Container item class for Tables view :
 * </p>
 * Created by Samir on 22/03/2015.
 */
public class TabItemArticle extends RecyclerItem {

    private CharSequence name, price;
    private Intent intent;
    private TextView textViewName, textViewPrice;

    public TabItemArticle() {
        super(R.layout.tabitem_product);
    }

    public CharSequence getName() {
        return this.name;
    }

    public void setName(CharSequence name) {
        this.name = name;

        try {
            this.textViewName.setText(this.name);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public CharSequence getPrice() {
        return this.price;
    }

    public void setPrice(CharSequence price) {
        this.price = price;

        try {
            this.textViewPrice.setText(this.price);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        this.textViewName = (TextView) getView().findViewById(R.id.tabitem_textview_name);
        this.textViewName.setText(this.name);

        this.textViewPrice = (TextView) getView().findViewById(R.id.tabitem_textview_price);
        this.textViewPrice.setText(this.price);

        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    view.getContext().startActivity(intent);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });

        int color = getView().getContext().getResources().getColor(R.color.background_material_light);
        getView().setOnTouchListener(new ColorOnTouchListener(color));
    }
}

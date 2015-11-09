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
public class TabItemSpend extends RecyclerItem {

    private CharSequence date, name, value;
    private Intent intent;
    private TextView textViewDate, textViewName, textViewValue;

    public TabItemSpend() {
        super(R.layout.tabitem_spend);
    }

    public CharSequence getDate() {
        return this.date;
    }

    public void setDate(CharSequence date) {
        this.date = date;

        try {
            this.textViewDate.setText(this.date);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
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

    public CharSequence getValue() {
        return this.value;
    }

    public void setValue(CharSequence value) {
        this.value = value;

        try {
            this.textViewValue.setText(this.value);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
        this.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        this.textViewDate = (TextView) getView().findViewById(R.id.tabitem_textview_date);
        this.textViewDate.setText(this.date);

        this.textViewName = (TextView) getView().findViewById(R.id.tabitem_textview_name);
        this.textViewName.setText(this.name);

        this.textViewValue = (TextView) getView().findViewById(R.id.tabitem_textview_value);
        this.textViewValue.setText(this.value);

        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
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

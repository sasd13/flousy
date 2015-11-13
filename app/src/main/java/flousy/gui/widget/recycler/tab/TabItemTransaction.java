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
public class TabItemTransaction extends RecyclerItem {

    private CharSequence date, title, value;
    private Intent intent;
    private TextView textViewDate, textViewTitle, textViewValue;

    public TabItemTransaction() {
        super(R.layout.tabitem_transaction);
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

    public CharSequence getTitle() {
        return this.title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        try {
            this.textViewTitle.setText(this.title);
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

        this.textViewDate = (TextView) getView().findViewById(R.id.tabitem_textview_daterealization);
        this.textViewDate.setText(this.date);

        this.textViewTitle = (TextView) getView().findViewById(R.id.tabitem_textview_title);
        this.textViewTitle.setText(this.title);

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

package com.sasd13.flousy.gui.widget.recycler.tab;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.sasd13.flousy.R;

import com.sasd13.flousy.gui.color.ColorOnTouchListener;
import com.sasd13.flousy.gui.widget.recycler.RecyclerItem;

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
        super(R.layout.tabitemtransaction);
    }

    public void setDate(CharSequence date) {
        this.date = date;

        try {
            this.textViewDate.setText(this.date);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        try {
            this.textViewTitle.setText(this.title);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setValue(CharSequence value) {
        this.value = value;

        try {
            this.textViewValue.setText(this.value);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
        this.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        findViews();
        bindViews();
        setOnClickListener();
        setOnTouchListener();
    }

    private void findViews() {
        this.textViewDate = (TextView) getView().findViewById(R.id.tabitemtransaction_textview_date);
        this.textViewTitle = (TextView) getView().findViewById(R.id.tabitemtransaction_textview_title);
        this.textViewValue = (TextView) getView().findViewById(R.id.tabitemtransaction_textview_value);
    }

    private void bindViews() {
        setDate(this.date);
        setTitle(this.title);
        setValue(this.value);
    }

    private void setOnClickListener() {
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
    }

    private void setOnTouchListener() {
        int color = getView().getContext().getResources().getColor(R.color.background_material_light);
        getView().setOnTouchListener(new ColorOnTouchListener(color));
    }
}

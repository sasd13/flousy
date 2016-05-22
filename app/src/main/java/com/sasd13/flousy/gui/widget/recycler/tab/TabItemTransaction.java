package com.sasd13.flousy.gui.widget.recycler.tab;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.color.ColorOnTouchListener;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.flousy.R;

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

    public void setDate(CharSequence date) {
        this.date = date;

        try {
            textViewDate.setText(date);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        try {
            textViewTitle.setText(title);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setValue(CharSequence value) {
        this.value = value;

        try {
            textViewValue.setText(value);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void setView(View view) {
        super.setView(view);

        findViews();
        bindViews();
        setOnClickListener();
        setOnTouchListener();
    }

    private void findViews() {
        textViewDate = (TextView) view.findViewById(R.id.tabitemtransaction_textview_date);
        textViewTitle = (TextView) view.findViewById(R.id.tabitemtransaction_textview_title);
        textViewValue = (TextView) view.findViewById(R.id.tabitemtransaction_textview_value);
    }

    private void bindViews() {
        setDate(date);
        setTitle(title);
        setValue(value);
    }

    private void setOnClickListener() {
        view.setOnClickListener(new View.OnClickListener() {
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
        int color = view.getContext().getResources().getColor(R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));
    }
}

package com.sasd13.flousy.gui.widget.recycler.tab;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.color.ColorOnTouchListener;
import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.flousy.R;

/**
 * <p>
 * Container item class for Tables view :
 * </p>
 * Created by Samir on 22/03/2015.
 */
public class TabItemOperation extends TabItem {

    private CharSequence date, title, amount;
    private Intent intent;
    private TextView textViewDate, textViewTitle, textViewAmount;

    public void setDate(CharSequence date) {
        this.date = date;

        if (textViewDate != null) {
            textViewDate.setText(date);
        }
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        if (textViewTitle != null) {
            textViewTitle.setText(title);
        }
    }

    public void setAmount(CharSequence amount) {
        this.amount = amount;

        if (textViewAmount != null) {
            textViewAmount.setText(amount);
        }
    }

    public void setIntent(Intent intent) {
        this.intent = intent;

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void bindView(View view) {
        super.bindView(view);

        findItemViews();
        bindItemView();
        setOnClickListener();
        setOnTouchListener();
    }

    private void findItemViews() {
        textViewDate = (TextView) view.findViewById(R.id.tabitem_operation_textview_date);
        textViewTitle = (TextView) view.findViewById(R.id.tabitem_operation_textview_title);
        textViewAmount = (TextView) view.findViewById(R.id.tabitem_operation_textview_amount);
    }

    private void bindItemView() {
        setDate(date);
        setTitle(title);
        setAmount(amount);
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
        int color = ContextCompat.getColor(view.getContext(), R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));
    }
}

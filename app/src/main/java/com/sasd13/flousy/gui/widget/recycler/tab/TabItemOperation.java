package com.sasd13.flousy.gui.widget.recycler.tab;

import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.flousy.R;

/**
 * <p>
 * Container item class for Tables view :
 * </p>
 * Created by Samir on 22/03/2015.
 */
public class TabItemOperation extends TabItem {

    private String date, amount;
    private TextView textViewDate, textViewAmount;
    private OnClickListener onClickListener;

    public TabItemOperation() {
        super(R.layout.tabitem_operation);
    }

    public void setDate(String date) {
        this.date = date;

        if (textViewDate != null) {
            textViewDate.setText(date);
        }
    }

    public void setAmount(String amount) {
        this.amount = amount;

        if (textViewAmount != null) {
            textViewAmount.setText(amount);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected void findItemViews() {
        super.findItemViews();

        textViewDate = (TextView) view.findViewById(R.id.tabitem_operation_textview_date);
        textViewAmount = (TextView) view.findViewById(R.id.tabitem_operation_textview_amount);
    }

    @Override
    protected void bindItemViews() {
        super.bindItemViews();

        setDate(date);
        setAmount(amount);
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClickOnRecyclerItem(TabItemOperation.this);
                }
            }
        });
    }
}

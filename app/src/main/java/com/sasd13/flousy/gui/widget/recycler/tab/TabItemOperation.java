package com.sasd13.flousy.gui.widget.recycler.tab;

import android.support.v4.content.ContextCompat;
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
public class TabItemOperation extends RecyclerItem {

    private long id;
    private String date, title, amount;
    private TextView textViewDate, textViewTitle, textViewAmount;
    private OnClickListener onClickListener;

    public TabItemOperation() {
        super(R.layout.tabitem_operation);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;

        if (textViewDate != null) {
            textViewDate.setText(date);
        }
    }

    public void setTitle(String title) {
        this.title = title;

        if (textViewTitle != null) {
            textViewTitle.setText(title);
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
    public void bindView(View view) {
        super.bindView(view);

        findItemViews();
        bindItemViews();
        setListeners();
    }

    protected void findItemViews() {
        textViewDate = (TextView) view.findViewById(R.id.tabitem_operation_textview_date);
        textViewTitle = (TextView) view.findViewById(R.id.tabitem_operation_textview_title);
        textViewAmount = (TextView) view.findViewById(R.id.tabitem_operation_textview_amount);
    }

    protected void bindItemViews() {
        setDate(date);
        setTitle(title);
        setAmount(amount);
    }

    protected void setListeners() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClickOnRecyclerItem(TabItemOperation.this);
            }
        });

        int color = ContextCompat.getColor(view.getContext(), R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));
    }
}

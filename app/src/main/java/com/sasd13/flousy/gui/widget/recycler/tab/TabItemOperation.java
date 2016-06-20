package com.sasd13.flousy.gui.widget.recycler.tab;

import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.flousy.R;

import java.util.Observable;

/**
 * <p>
 * Container item class for Tables view :
 * </p>
 * Created by Samir on 22/03/2015.
 */
public class TabItemOperation extends TabItem {

    private TextView textViewDate, textViewAmount;

    public TabItemOperation() {
        super(R.layout.tabitem_operation);
    }

    @Override
    protected void findViews() {
        super.findViews();

        textViewDate = (TextView) view.findViewById(R.id.tabitem_operation_textview_date);
        textViewAmount = (TextView) view.findViewById(R.id.tabitem_operation_textview_amount);
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);

        TabModelOperation tabModelOperation = (TabModelOperation) observable;

        if (textViewDate != null) {
            textViewDate.setText(tabModelOperation.getDate());
        }

        if (textViewAmount != null) {
            textViewAmount.setText(tabModelOperation.getAmount());
        }
    }
}

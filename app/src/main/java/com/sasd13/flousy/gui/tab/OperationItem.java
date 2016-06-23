package com.sasd13.flousy.gui.tab;

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
public class OperationItem extends TabItem {

    private TextView textViewDate, textViewAmount;

    public OperationItem() {
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

        OperationModel operationModel = (OperationModel) observable;

        if (textViewDate != null) {
            textViewDate.setText(operationModel.getDate());
        }

        if (textViewAmount != null) {
            textViewAmount.setText(operationModel.getAmount());
        }
    }
}

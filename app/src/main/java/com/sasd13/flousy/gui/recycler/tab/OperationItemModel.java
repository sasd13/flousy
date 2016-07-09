package com.sasd13.flousy.gui.recycler.tab;

import android.content.Context;

import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.recycler.ILabelizedItemModel;
import com.sasd13.androidex.util.DateTimeHelper;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.gui.recycler.RecyclerItemType;

import java.util.Observable;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationItemModel extends Observable implements ILabelizedItemModel {

    private Operation operation;
    private Context context;

    public OperationItemModel(Operation operation, Context context) {
        this.operation = operation;
        this.context = context;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public IItemType getItemType() {
        return RecyclerItemType.TAB_OPERATION;
    }

    @Override
    public String getLabel() {
        return operation.getTitle();
    }

    public String getDate() {
        return DateTimeHelper.format(
                operation.getDateRealization(),
                DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.Format.SHORT));
    }

    public String getAmount() {
        return String.valueOf(operation.getAmount());
    }
}

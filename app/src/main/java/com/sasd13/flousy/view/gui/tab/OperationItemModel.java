package com.sasd13.flousy.view.gui.tab;

import android.content.Context;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.ISelectable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.androidex.util.DateTimeHelper;

import java.util.Observable;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationItemModel extends Observable implements IRecyclerItemModel, ILabelizable, ISelectable {

    private Operation operation;
    private boolean selected;

    public OperationItemModel(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    @Override
    public IRecyclerItemType getItemType() {
        return EnumTabItemType.OPERATION;
    }

    @Override
    public String getLabel() {
        return operation.getTitle();
    }

    public String getDate(Context context) {
        return DateTimeHelper.format(
                operation.getDateRealization(),
                DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.EnumFormat.SHORT));
    }

    public String getAmount() {
        return String.valueOf(operation.getAmount());
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

        setChanged();
        notifyObservers();
    }
}

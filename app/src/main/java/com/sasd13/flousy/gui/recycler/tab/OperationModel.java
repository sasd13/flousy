package com.sasd13.flousy.gui.recycler.tab;

import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.IClickable;
import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.ILongClickable;
import com.sasd13.androidex.gui.widget.recycler.ILabelizedModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerModel;
import com.sasd13.flousy.gui.recycler.RecyclerItemType;

import java.util.Observable;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationModel extends Observable implements IRecyclerModel, ILabelizedModel, IClickable, ILongClickable {

    private String date, label, amount;
    private Action actionClick, actionLongClick;

    @Override
    public IItemType getItemType() {
        return RecyclerItemType.TAB_OPERATION;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;

        setChanged();
        notifyObservers();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;

        setChanged();
        notifyObservers();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;

        setChanged();
        notifyObservers();
    }

    @Override
    public Action getActionClick() {
        return actionClick;
    }

    public void setActionClick(Action actionClick) {
        this.actionClick = actionClick;

        setChanged();
        notifyObservers();
    }

    @Override
    public Action getActionLongClick() {
        return actionLongClick;
    }

    public void setActionLongClick(Action actionLongClick) {
        this.actionLongClick = actionLongClick;

        setChanged();
        notifyObservers();
    }
}

package com.sasd13.flousy.gui.recycler.tab;

import android.content.Context;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.IType;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.util.DateTimeHelper;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.gui.recycler.MyRecyclerFactoryType;

import java.util.Observable;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationItemModel extends Observable implements IRecyclerItemModel, ILabelizable {

    private Operation operation;

    public OperationItemModel(Operation operation) {
        this.operation = operation;
    }

    @Override
    public IType getType() {
        return MyRecyclerFactoryType.TAB_OPERATION;
    }

    @Override
    public String getLabel() {
        return operation.getTitle();
    }

    public String getDate(Context context) {
        return DateTimeHelper.format(
                operation.getDateRealization(),
                DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.Format.SHORT));
    }

    public String getAmount() {
        return String.valueOf(operation.getAmount());
    }
}

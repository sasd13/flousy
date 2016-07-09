package com.sasd13.flousy.gui.recycler.tab;

import android.content.Intent;
import android.view.View;

import com.sasd13.androidex.gui.widget.recycler.IRecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemBuilder;
import com.sasd13.flousy.OperationActivity;
import com.sasd13.flousy.content.Extra;

/**
 * Created by ssaidali2 on 09/07/2016.
 */
public class OperationItemBuilder extends RecyclerItemBuilder {

    public OperationItemBuilder(IRecyclerFactory recyclerFactory) {
        super(recyclerFactory);
    }

    @Override
    public RecyclerItem build(IRecyclerItemModel recyclerModel) {
        RecyclerItem recyclerItem = super.build(recyclerModel);

        setOnClickListener(recyclerItem, (OperationItemModel) recyclerModel);

        return recyclerItem;
    }

    private void setOnClickListener(RecyclerItem recyclerItem, final OperationItemModel operationItemModel) {
        recyclerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OperationActivity.class);
                intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
                intent.putExtra(Extra.OPERATION_ID, operationItemModel.getOperation().getId());

                context.startActivity(intent);
            }
        });
    }
}

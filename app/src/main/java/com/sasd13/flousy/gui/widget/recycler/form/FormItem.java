package com.sasd13.flousy.gui.widget.recycler.form;

import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.flousy.R;

import java.util.Observable;
import java.util.Observer;

public class FormItem extends RecyclerItem implements Observer {

    public interface Action {
        void execute(FormItem formItem);
    }

    private int id;
    private String label;
    protected Action action;
    protected TextView textViewLabel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;

        if (textViewLabel != null) {
            textViewLabel.setText(label);
        }
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public void bindView(View view) {
        super.bindView(view);

        findItemViews();
        bindItemViews();
        setListener();
    }

    protected void findItemViews() {
        textViewLabel = (TextView) view.findViewById(R.id.formitem_textview_label);
    }

    protected void bindItemViews() {
        setLabel(label);
    }

    protected void setListener() {
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    action.execute(FormItem.this);
                }
            });
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        bindItemViews();
    }
}

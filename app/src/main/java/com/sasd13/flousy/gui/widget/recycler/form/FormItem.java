package com.sasd13.flousy.gui.widget.recycler.form;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sasd13.androidex.gui.color.ColorOnTouchListener;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.flousy.R;

import java.util.Observable;
import java.util.Observer;

public class FormItem extends RecyclerItem implements Observer {

    private FormItemInput input;
    private FormItemAction action;
    private TextView textViewName, textViewValue;
    private boolean textViewValueDisabled;

    public FormItem() {
        input = new FormItemInput();

        input.addObserver(this);
    }

    protected void setName() {
        if (textViewName != null) {
            textViewName.setText(input.getName());
        }
    }

    protected void setValue() {
        if (textViewValue != null) {
            textViewValue.setText(input.getValue());
        }
    }

    public FormItemInput getInput() {
        return input;
    }

    public void setAction(FormItemAction action) {
        this.action = action;
    }

    @Override
    public void bindView(View view) {
        super.bindView(view);

        findItemViews();
        bindItemViews();
        setOnClickListener();
        setOnTouchListener();
    }

    private void findItemViews() {
        textViewName = (TextView) view.findViewById(R.id.formitem_textview_name);
        textViewValue = (TextView) view.findViewById(R.id.formitem_textview_value);
    }

    private void bindItemViews() {
        setName();
        setValue();

        switchVisibilityOfTextViewValue();
    }

    private void setOnClickListener() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.execute(input);
            }
        });
    }

    private void setOnTouchListener() {
        int color = ContextCompat.getColor(view.getContext(), R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));
    }

    private void switchVisibilityOfTextViewValue() {
        if (textViewName != null && textViewValue != null) {
            LinearLayout.LayoutParams params;

            if (textViewValueDisabled) {
                textViewValue.setVisibility(View.GONE);
                params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
            } else {
                textViewValue.setVisibility(View.VISIBLE);
                params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            }

            textViewName.setLayoutParams(params);
        }
    }

    public void setTextViewValueDisabled(boolean textViewValueDisabled) {
        this.textViewValueDisabled = textViewValueDisabled;

        switchVisibilityOfTextViewValue();
    }

    @Override
    public void update(Observable observable, Object o) {
        bindItemViews();
    }
}

package com.sasd13.flousy.gui.widget.recycler.form;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sasd13.androidex.gui.color.ColorOnTouchListener;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.flousy.R;

public class FormItem<T> extends RecyclerItem {

    public interface Action {

        void execute(FormItem formItem);
    }

    private int id;
    private String name, value;
    private T tag;
    private Action action;
    private TextView textViewName, textViewValue;
    private boolean textViewValueDisabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        if (textViewName != null) {
            textViewName.setText(name);
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;

        if (textViewValue != null) {
            textViewValue.setText(value);
        }
    }

    public T getTag() {
        return tag;
    }

    public void setTag(T tag) {
        this.tag = tag;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public void bindView(View view) {
        super.bindView(view);

        findItemViews();
        bindItemViews();
        setOnClickListener();
        setOnTouchListener();
        switchVisibilityOfTextViewValue();
    }

    private void findItemViews() {
        textViewName = (TextView) view.findViewById(R.id.formitem_textview_name);
        textViewValue = (TextView) view.findViewById(R.id.formitem_textview_value);
    }

    private void bindItemViews() {
        setName(name);
        setValue(value);
    }

    private void setOnClickListener() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.execute(FormItem.this);
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
}

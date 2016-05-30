package com.sasd13.flousy.gui.widget.recycler.form;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.color.ColorOnTouchListener;
import com.sasd13.flousy.R;
import com.sasd13.flousy.gui.widget.recycler.form.input.FormInput;

public class FormItemText extends FormItem {

    private FormInput input;
    private TextView textViewValue;

    public FormInput getInput() {
        return input;
    }

    public void setInput(FormInput input) {
        this.input = input;
    }

    @Override
    public void bindView(View view) {
        super.bindView(view);

        setOnTouchListener();
    }

    @Override
    protected void findItemViews() {
        super.findItemViews();

        textViewValue = (TextView) view.findViewById(R.id.formitem_textview_value);
    }

    @Override
    protected void bindItemViews() {
        super.bindItemViews();

        if (textViewValue != null) {
            textViewValue.setText(input.getStringValue());
        }
    }

    private void setOnTouchListener() {
        int color = ContextCompat.getColor(view.getContext(), R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));
    }
}

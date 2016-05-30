package com.sasd13.flousy.gui.widget.recycler.form;

import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.sasd13.flousy.R;
import com.sasd13.flousy.gui.widget.recycler.form.input.FormInput;

public class FormItemToggle extends FormItem {

    private FormInput<Boolean> input;
    private ToggleButton toggleButtonValue;

    public FormInput<Boolean> getInput() {
        return input;
    }

    public void setInput(FormInput<Boolean> input) {
        this.input = input;
    }

    @Override
    protected void findItemViews() {
        super.findItemViews();

        toggleButtonValue = (ToggleButton) view.findViewById(R.id.formitem_togglebutton_value);
    }

    @Override
    protected void bindItemViews() {
        super.bindItemViews();

        toggleButtonValue.setChecked(input.getValue());
    }

    @Override
    protected void setListener() {
        if (toggleButtonValue != null) {
            toggleButtonValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    input.setValue(b);
                }
            });
        }
    }
}

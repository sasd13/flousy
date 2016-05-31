package com.sasd13.flousy.gui.widget.recycler.form;

import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.sasd13.flousy.R;

public class FormItemToggle extends FormItem {

    private FormInput<Boolean> input;
    private ToggleButton toggleButtonValue;

    public FormItemToggle() {
        input = new FormInput<Boolean>() {
            @Override
            public String getStringValue() {
                return toggleButtonValue.isChecked()
                        ? String.valueOf(toggleButtonValue.getTextOn())
                        : String.valueOf(toggleButtonValue.getTextOff());
            }
        };

        input.addObserver(this);
    }

    public void setTextOn(String textOn) {
        toggleButtonValue.setTextOn(textOn);
    }

    public void setTextOff(String textOff) {
        toggleButtonValue.setTextOff(textOff);
    }

    public FormInput<Boolean> getInput() {
        return input;
    }

    @Override
    protected void findItemViews() {
        super.findItemViews();

        toggleButtonValue = (ToggleButton) view.findViewById(R.id.formitem_togglebutton_value);
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        toggleButtonValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                action.execute(FormItemToggle.this);
            }
        });
    }
}

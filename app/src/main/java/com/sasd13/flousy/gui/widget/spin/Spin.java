package com.sasd13.flousy.gui.widget.spin;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Spin {

    private Spinner spinner;
    private ArrayAdapter<String> spinAdapter;

    public Spin(Context context) {
        this.spinAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        this.spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public void addItem(String item) {
        this.spinAdapter.add(item);
    }

    public void removeItem(String item) {
        this.spinAdapter.remove(item);
    }

    public String getItem(int index) {
        return this.spinAdapter.getItem(index);
    }

    public String getSelectedItem() {
        return getItem(getSelectedItemPosition());
    }

    public int getSelectedItemPosition() {
        return this.spinner.getSelectedItemPosition();
    }

    public int size() {
        return this.spinAdapter.getCount();
    }

    public void clearItems() {
        this.spinAdapter.clear();
    }

    public void adapt(Spinner spinner, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.spinner = spinner;
        this.spinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    public void validate() {
        this.spinner.setAdapter(this.spinAdapter);
        this.spinner.setSelection(0, false);
    }

    public void setVisible(boolean visible) {
        if (visible) {
            this.spinner.setVisibility(View.VISIBLE);
        } else {
            this.spinner.setVisibility(View.INVISIBLE);
        }
    }
}

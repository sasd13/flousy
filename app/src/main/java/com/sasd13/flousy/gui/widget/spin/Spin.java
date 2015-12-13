package com.sasd13.flousy.gui.widget.spin;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Spin {

    private Spinner spinner;
    private ArrayAdapter<String> spinAdapter;

    public Spin(Context context, Spinner spinner, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.spinner = spinner;
        this.spinAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);

        adapt();

        this.spinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    private void adapt() {
        this.spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner.setAdapter(this.spinAdapter);
    }

    public void addItem(String item) {
        this.spinAdapter.add(item);
    }

    public void removeItem(String item) {
        this.spinAdapter.remove(item);
    }

    public void clearItems() {
        this.spinAdapter.clear();
    }

    public String getSelectedItem() {
        return this.spinAdapter.getItem(getSelectedItemPosition());
    }

    public int getSelectedItemPosition() {
        return this.spinner.getSelectedItemPosition();
    }

    public void resetPosition() {
        this.spinner.setSelection(0, false);
    }
}

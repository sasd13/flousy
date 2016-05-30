package com.sasd13.flousy.gui.widget.recycler.form.input;

import java.util.Observable;

/**
 * Created by Samir on 30/05/2016.
 */
public abstract class FormInput<T> extends Observable {

    private String label, hint;
    private T value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;

        setChanged();
        notifyObservers();
    }

    public abstract String getStringValue();
}

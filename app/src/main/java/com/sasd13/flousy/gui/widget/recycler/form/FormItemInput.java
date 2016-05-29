package com.sasd13.flousy.gui.widget.recycler.form;

import java.util.Observable;

/**
 * Created by Samir on 29/05/2016.
 */
public class FormItemInput extends Observable {

    private String name, value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        setChanged();
        notifyObservers();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;

        setChanged();
        notifyObservers();
    }
}

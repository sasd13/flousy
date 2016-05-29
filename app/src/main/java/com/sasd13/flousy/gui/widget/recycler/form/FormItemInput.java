package com.sasd13.flousy.gui.widget.recycler.form;

import java.util.Observable;

/**
 * Created by Samir on 29/05/2016.
 */
public class FormItemInput extends Observable {

    private int id;
    private String name, hint, value;

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

        setChanged();
        notifyObservers();
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
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

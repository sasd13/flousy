package com.sasd13.flousy.gui.widget.recycler.tab;

import com.sasd13.androidex.gui.widget.recycler.tab.TabModel;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class TabModelOperation extends TabModel {

    private String date, amount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;

        setChanged();
        notifyObservers();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;

        setChanged();
        notifyObservers();
    }
}

package com.sasd13.flousy.scope;

import java.util.Observable;

/**
 * Created by ssaidali2 on 29/04/2017.
 */

public class Scope extends Observable {

    private boolean cancelled, loading;

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;

        setChanged();
        notifyObservers();
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;

        setChanged();
        notifyObservers();
    }
}

package flousy.gui.widget.recycler;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Samir on 17/06/2015.
 */
public class RecyclerAdapterDataObserver extends RecyclerView.AdapterDataObserver {

    private RecyclerView recyclerView;

    public RecyclerAdapterDataObserver(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onChanged() {
        super.onChanged();

        this.recyclerView.removeAllViews();
        this.recyclerView.postInvalidate();
    }
}

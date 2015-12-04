package com.sasd13.flousy.gui.widget.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.sasd13.flousy.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private List<RecyclerItem> listRecyclerItems;
    private int recyclerItemLayout;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewStub stub;

        public ViewHolder(View view) {
            super(view);

            stub = (ViewStub) view.findViewById(R.id.recyclerviewitem_viewstub);
        }
    }

    public RecyclerAdapter(List<RecyclerItem> listRecyclerItems, int recyclerItemLayout) {
        this.listRecyclerItems = listRecyclerItems;
        this.recyclerItemLayout = recyclerItemLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.recyclerItemLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewStub viewStub = ((ViewHolder) viewHolder).stub;

        try {
            this.listRecyclerItems.get(position).inflate(viewStub);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return this.listRecyclerItems.size();
    }
}

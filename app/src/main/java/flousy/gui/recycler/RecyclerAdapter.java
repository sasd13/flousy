package flousy.gui.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.diderot.android.flousy.R;

import java.util.ArrayList;

/**
 * Created by Samir on 22/03/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<AbstractRecyclerItem> listItem;
    private int itemStubLayout;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewStub stub;

        public ViewHolder(View view) {
            super(view);

            stub = (ViewStub) view.findViewById(R.id.recyclerview_item_layout_viewstub);
        }
    }

    public RecyclerAdapter(ArrayList<AbstractRecyclerItem> listItem, int itemStubLayout) {
        this.listItem = listItem;
        this.itemStubLayout = itemStubLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.itemStubLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        AbstractRecyclerItem abstractRecyclerItem = this.listItem.get(position);

        if(abstractRecyclerItem.getView() == null) {
            abstractRecyclerItem.inflate(((ViewHolder) viewHolder).stub);
        }
    }

    @Override
    public int getItemCount() {
        return this.listItem.size();
    }
}

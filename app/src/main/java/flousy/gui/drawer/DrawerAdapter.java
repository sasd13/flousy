package flousy.gui.drawer;

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
public class DrawerAdapter extends RecyclerView.Adapter {

    private ArrayList<AbstractDrawerItem> listAbstractDrawerItem;
    private int itemStubLayout;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewStub stub;

        public ViewHolder(View view) {
            super(view);

            stub = (ViewStub) view.findViewById(R.id.drawer_viewstub);
        }
    }

    public DrawerAdapter(ArrayList<AbstractDrawerItem> listAbstractDrawerItem, int itemStubLayout) {
        this.listAbstractDrawerItem = listAbstractDrawerItem;
        this.itemStubLayout = itemStubLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.itemStubLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        AbstractDrawerItem abstractDrawerItem = this.listAbstractDrawerItem.get(position);

        if(abstractDrawerItem.getView() == null) {
            abstractDrawerItem.inflate(((ViewHolder) viewHolder).stub);
        }
    }

    @Override
    public int getItemCount() {
        return this.listAbstractDrawerItem.size();
    }
}

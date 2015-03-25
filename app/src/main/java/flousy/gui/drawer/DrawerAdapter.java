package flousy.gui.drawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.diderot.android.flousy.R;

import java.util.ArrayList;

import flousy.gui.listener.CustomOnTouchListener;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<AbstractDrawerItem> listAbstractDrawerItem;
    private int itemStubLayout;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewStub stub;

        public ViewHolder(View view) {
            super(view);

            stub = (ViewStub) view.findViewById(R.id.drawer_viewstub);
        }
    }

    public DrawerAdapter(Context context, ArrayList<AbstractDrawerItem> listAbstractDrawerItem, int itemStubLayout) {
        this.context = context;
        this.listAbstractDrawerItem = listAbstractDrawerItem;
        this.itemStubLayout = itemStubLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemStubLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final AbstractDrawerItem abstractDrawerItem = this.listAbstractDrawerItem.get(position);

        if(abstractDrawerItem.getView() == null) {
            ViewStub viewStub =  ((ViewHolder) viewHolder).stub;
            viewStub.setLayoutResource(abstractDrawerItem.getLayoutResource());
            View convertView = viewStub.inflate();
            abstractDrawerItem.parse(convertView);

            if(abstractDrawerItem instanceof DrawerItem) {
                abstractDrawerItem.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(((DrawerItem) abstractDrawerItem).getIntent() != null) {
                            context.startActivity(((DrawerItem) abstractDrawerItem).getIntent());
                        }
                    }
                });
                abstractDrawerItem.getView().setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        view.performClick();
                        return false;
                    }
                });
                int color = this.context.getResources().getColor(R.color.background_material_light);
                abstractDrawerItem.getView().setOnTouchListener(new CustomOnTouchListener(color));
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.listAbstractDrawerItem.size();
    }
}

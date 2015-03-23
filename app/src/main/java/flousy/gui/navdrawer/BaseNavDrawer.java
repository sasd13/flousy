package flousy.gui.navdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Samir on 22/03/2015.
 */
public class BaseNavDrawer extends NavDrawer {

    public BaseNavDrawer(Context context) {
        super(context);
    }

    @Override
    public void adapt(ListView listView) {
        BaseNavDrawerAdapter baseNavDrawerAdapter = new BaseNavDrawerAdapter(this);
        listView.setAdapter(baseNavDrawerAdapter);
    }
}

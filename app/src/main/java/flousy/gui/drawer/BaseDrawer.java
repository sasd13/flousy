package flousy.gui.drawer;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Samir on 22/03/2015.
 */
public class BaseDrawer extends Drawer {

    public BaseDrawer() {
        super();
    }

    @Override
    public void adapt(ListView listView) {
        BaseDrawerAdapter baseDrawerAdapter = new BaseDrawerAdapter(this);
        listView.setAdapter(baseDrawerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.performClick();
            }
        });
    }
}

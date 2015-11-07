package flousy.gui.widget.recycler.drawer;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;

import com.example.flousy.R;

import flousy.gui.color.ColorOnTouchListener;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerItemIntentable extends DrawerItem {

    private Intent intent;

    public DrawerItemIntentable() {
        super(R.layout.draweritem);
    }

    public DrawerItemIntentable(int layoutResource) {
        super(layoutResource);
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        getView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });

        int color = getView().getContext().getResources().getColor(R.color.background_material_light);
        getView().setOnTouchListener(new ColorOnTouchListener(color));
    }
}
package flousy.gui.recycler.drawer;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;

import com.diderot.android.flousy.R;

import flousy.gui.color.ColorOnTouchListener;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerItemBase extends DrawerItem {

    private Intent intent;

    public DrawerItemBase() {
        super(R.layout.draweritem);

        this.intent = null;
    }

    public DrawerItemBase(int layoutResource) {
        super(layoutResource);

        this.intent = null;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    @Override
    public View inflate(ViewStub viewStub) {
        View view = super.inflate(viewStub);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent != null) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                }
            }
        });
        int color = view.getContext().getResources().getColor(R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));

        return view;
    }
}

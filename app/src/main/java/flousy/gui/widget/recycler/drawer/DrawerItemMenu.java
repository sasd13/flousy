package flousy.gui.widget.recycler.drawer;

import android.view.View;
import android.view.ViewStub;

import com.diderot.android.flousy.MotherActivity;
import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerItemMenu extends DrawerItemBase {

    private int color;
    private View colorView;

    public DrawerItemMenu() {
        super(R.layout.draweritem_menu);

        this.color = 0;
        this.colorView = null;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;

        if(this.colorView != null) {
            this.colorView.setBackgroundColor(this.color);
        }
    }

    @Override
    public View inflate(ViewStub viewStub) {
        View view = super.inflate(viewStub);

        this.colorView = view.findViewById(R.id.draweritem_menu_colorview);
        if(this.color == 0) {
            this.color = view.getContext().getResources().getColor(MotherActivity.APP_COLOR);
        }
        this.colorView.setBackgroundColor(this.color);

        return view;
    }
}

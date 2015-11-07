package flousy.gui.widget.recycler.drawer;

import android.view.View;
import android.view.ViewStub;

import com.example.flousy.MotherActivity;
import com.example.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerItemHomeMenu extends DrawerItemIntentable {

    private int color;
    private View colorView;

    public DrawerItemHomeMenu() {
        super(R.layout.draweritem_menu);
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;

        try {
            this.colorView.setBackgroundColor(this.color);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        this.colorView = getView().findViewById(R.id.draweritem_menu_colorview);
        if (this.color == 0) {
            this.color = getView().getContext().getResources().getColor(MotherActivity.APP_COLOR);
        }
        this.colorView.setBackgroundColor(this.color);
    }
}

package flousy.gui.recycler.tab;

import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.diderot.android.flousy.R;

import flousy.gui.recycler.AbstractRecyclerItem;

/**
 * Created by Samir on 22/03/2015.
 */
public class TabItemTitle extends AbstractRecyclerItem {

    public TabItemTitle() {
        super(R.layout.tabitem_title);
    }

    @Override
    public View inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(getLayoutResource());
        View view = viewStub.inflate();

        setView(view);

        return view;
    }
}

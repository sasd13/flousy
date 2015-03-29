package flousy.gui.recycler.drawer;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.diderot.android.flousy.R;

import flousy.gui.listener.CustomOnTouchListener;
import flousy.gui.recycler.AbstractRecyclerItem;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerItem extends AbstractRecyclerItem {

    private CharSequence text;
    private TextView textView;
    private Intent intent;

    public DrawerItem() {
        super(R.layout.draweritem);

        this.text = "Item";
        this.textView = null;
        this.intent = null;
    }

    public DrawerItem(int layoutResource) {
        super(layoutResource);

        this.text = "Item";
        this.textView = null;
        this.intent = null;
    }

    public CharSequence getText() {
        return this.text;
    }

    public void setText(CharSequence text) {
        this.text = text;

        if(this.textView != null) {
            this.textView.setText(this.text);
        }
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    @Override
    public View inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(getLayoutResource());
        View view = viewStub.inflate();
        if (view == null) {
            return null;
        }

        setView(view);

        this.textView = (TextView) view.findViewById(R.id.draweritem_textview);
        this.textView.setText(this.text);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent != null) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performClick();
                return false;
            }
        });
        int color = view.getContext().getResources().getColor(R.color.background_material_light);
        view.setOnTouchListener(new CustomOnTouchListener(color));

        return view;
    }
}

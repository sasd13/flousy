package flousy.gui.navdrawer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diderot.android.flousy.MotherActivity;
import com.diderot.android.flousy.R;

import java.util.ArrayList;

/**
 * Created by Samir on 22/03/2015.
 */
public class BaseNavDrawerItemGroup extends NavDrawerItem {

    private Context context;
    private CharSequence groupTitle;
    private TextView groupTextView;
    private ArrayList<NavDrawerItem> listNavDrawerItem;

    public BaseNavDrawerItemGroup(Context context, CharSequence itemGroupTitle) {
        super(R.layout.navdraweritemgroup_base);

        this.context = context;
        this.groupTitle = itemGroupTitle;
        this.groupTextView = null;
        this.listNavDrawerItem = new ArrayList<NavDrawerItem>();
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CharSequence getGroupTitle() {
        return this.groupTitle;
    }

    public void setGroupTitle(CharSequence groupTitle) {
        this.groupTitle = groupTitle;
    }

    public NavDrawerItem getChild(int index) {
        if(index < 0 || index > (this.listNavDrawerItem.size() - 1)) {
            return null;
        }

        return this.listNavDrawerItem.get(index);
    }

    public boolean addChild(NavDrawerItem navDrawerItem) {
        if(this.listNavDrawerItem.contains(navDrawerItem) == true) {
            return false;
        }

        return this.listNavDrawerItem.add(navDrawerItem);
    }

    public boolean removeChild(Object object) {
        if(object instanceof NavDrawerItem == false) {
            return false;
        }

        return this.listNavDrawerItem.remove(object);
    }

    public int count() {
        return this.listNavDrawerItem.size();
    }

    @Override
    public void inflate(View view) {
        setView(view);

        ViewGroup viewGroup = (ViewGroup) view;

        this.groupTextView = (TextView) viewGroup.findViewWithTag("navdraweritemgroup_textview");
        if(this.groupTextView != null) {
            this.groupTextView.setText(this.groupTitle.toString().toUpperCase());
            this.groupTextView.setTypeface(Typeface.DEFAULT_BOLD);
            this.groupTextView.setTextColor(getContext().getResources().getColor(MotherActivity.APP_COLOR));
        }

        View viewDivider = viewGroup.findViewWithTag("navdraweritemgroup_textview_view_divider");
        viewDivider.setBackgroundColor(getContext().getResources().getColor(MotherActivity.APP_COLOR));

        LinearLayout linearLayout = (LinearLayout) viewGroup.findViewWithTag("navdraweritemgroup_base_linearlayout");
        if(linearLayout != null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View viewChild = null;
            for(NavDrawerItem navDrawerItem : this.listNavDrawerItem) {
                viewChild = inflater.inflate(navDrawerItem.getLayoutResource(), null);
                navDrawerItem.inflate(viewChild);
                linearLayout.addView(viewChild);
            }
        }
    }
}

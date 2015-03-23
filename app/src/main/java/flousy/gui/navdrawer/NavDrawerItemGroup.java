package flousy.gui.navdrawer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class NavDrawerItemGroup extends AbstractNavDrawerItem {

    private Context context;
    private CharSequence groupTitle;
    private TextView groupTextView;
    private ListNavDrawerItem listNavDrawerItem;

    public NavDrawerItemGroup(Context context, CharSequence itemGroupTitle) {
        super(R.layout.navdraweritemgroup_base);

        this.context = context;
        this.groupTitle = itemGroupTitle;
        this.groupTextView = null;
        this.listNavDrawerItem = new ListNavDrawerItem();
    }

    public NavDrawerItemGroup(Context context, CharSequence itemGroupTitle, int itemGroupLayoutResource) {
        super(itemGroupLayoutResource);

        this.context = context;
        this.groupTitle = itemGroupTitle;
        this.groupTextView = null;
        this.listNavDrawerItem = new ListNavDrawerItem();
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

    public ListNavDrawerItem getListNavDrawerItem() {
        return this.listNavDrawerItem;
    }

    public void setListNavDrawerItem(ListNavDrawerItem listNavDrawerItem) {
        this.listNavDrawerItem = listNavDrawerItem;
    }

    public int count() {
        return this.listNavDrawerItem.size();
    }

    public AbstractNavDrawerItem getChild(int position) {
        return this.listNavDrawerItem.get(position);
    }

    public boolean addChild(AbstractNavDrawerItem abstractNavDrawerItem) {
        return this.listNavDrawerItem.add(abstractNavDrawerItem);
    }

    public boolean removeChild(int position) {
        AbstractNavDrawerItem abstractNavDrawerItem = this.listNavDrawerItem.get(position);

        return this.listNavDrawerItem.remove(abstractNavDrawerItem);
    }

    @Override
    public View inflate() {
        ViewGroup viewGroup = (ViewGroup) getView();

        if(viewGroup != null) {
            this.groupTextView = (TextView) viewGroup.findViewWithTag("navdraweritemgroup_textview");
            if(this.groupTextView != null) {
                this.groupTextView.setText(this.groupTitle.toString().toUpperCase());
                this.groupTextView.setTypeface(Typeface.DEFAULT_BOLD);
            }

            LinearLayout linearLayout = (LinearLayout) viewGroup.findViewWithTag("navdraweritemgroup_linearlayout");
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = null;
            for(AbstractNavDrawerItem navDrawerItem : this.listNavDrawerItem) {
                view = inflater.inflate(navDrawerItem.getItemLayoutResource(), null);
                navDrawerItem.setView(view);
                navDrawerItem.inflate();
                linearLayout.addView(view);
            }
            linearLayout.invalidate();
        }

        return viewGroup;
    }
}

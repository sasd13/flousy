package flousy.gui.navdrawer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Samir on 22/03/2015.
 */
public class NavDrawerItemGroup extends NavDrawerItem {

    private CharSequence groupTitle;
    private TextView groupTextView;
    private ListNavDrawerItem listNavDrawerItem;

    public NavDrawerItemGroup(Context context, int itemGroupLayoutResource, CharSequence itemGroupTitle) {
        super(context, itemGroupLayoutResource);

        this.groupTitle = itemGroupTitle;
        this.groupTextView = null;
        this.listNavDrawerItem = new ListNavDrawerItem();
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

    public NavDrawerItem getChild(int position) {
        return this.listNavDrawerItem.get(position);
    }

    public boolean addChild(NavDrawerItem navDrawerItem) {
        return this.listNavDrawerItem.add(navDrawerItem);
    }

    public boolean removeChild(int position) {
        NavDrawerItem navDrawerItem = this.listNavDrawerItem.get(position);

        return this.listNavDrawerItem.remove(navDrawerItem);
    }

    @Override
    public View inflate() {
        ViewGroup viewGroup = (ViewGroup) getView();

        if(viewGroup != null) {
            this.groupTextView = (TextView) viewGroup.findViewWithTag("navdraweritemgroup_textview");
            if(this.groupTextView != null) {
                this.groupTextView.setText(this.groupTitle);
            }

            ListView listView = (ListView) viewGroup.findViewWithTag("navdraweritemgroup_listview");
            listView.setAdapter(new NavDrawerAdapter(this.listNavDrawerItem));
        }

        return viewGroup;
    }
}

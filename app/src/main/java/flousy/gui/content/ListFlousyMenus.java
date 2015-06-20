package flousy.gui.content;

import android.content.Context;
import android.content.Intent;

import com.example.flousy.ConsultActivity;
import com.example.flousy.FinancesActivity;
import com.example.flousy.FriendsActivity;
import com.example.flousy.NewActivity;
import com.example.flousy.OffersActivity;
import com.example.flousy.R;
import com.example.flousy.SettingsActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 30/03/2015.
 */
public class ListFlousyMenus implements Iterable {

    private static ListFlousyMenus instance = null;

    private List<FlousyMenu> list;

    private ListFlousyMenus(Context context) {
        this.list = new ArrayList<>();

        this.list.add(new FlousyMenu(
                context.getResources().getString(R.string.activity_new_name),
                context.getResources().getDrawable(R.drawable.griditem_new),
                context.getResources().getColor(R.color.customGreen),
                new Intent(context, NewActivity.class)));

        this.list.add(new FlousyMenu(
                context.getResources().getString(R.string.activity_consult_name),
                context.getResources().getDrawable(R.drawable.griditem_consult),
                context.getResources().getColor(R.color.customRed),
                new Intent(context, ConsultActivity.class)));

        this.list.add(new FlousyMenu(
                context.getResources().getString(R.string.activity_finances_name),
                context.getResources().getDrawable(R.drawable.griditem_finances),
                context.getResources().getColor(R.color.customOrange),
                new Intent(context, FinancesActivity.class)));

        this.list.add(new FlousyMenu(
                context.getResources().getString(R.string.activity_friends_name),
                context.getResources().getDrawable(R.drawable.griditem_friends),
                context.getResources().getColor(R.color.customBlue),
                new Intent(context, FriendsActivity.class)));

        this.list.add(new FlousyMenu(
                context.getResources().getString(R.string.activity_offers_name),
                context.getResources().getDrawable(R.drawable.griditem_offers),
                context.getResources().getColor(R.color.customPurple),
                new Intent(context, OffersActivity.class)));

        this.list.add(new FlousyMenu(
                context.getResources().getString(R.string.activity_settings_name),
                context.getResources().getDrawable(R.drawable.griditem_settings),
                context.getResources().getColor(R.color.customBrown),
                new Intent(context, SettingsActivity.class)));
    }

    public static synchronized ListFlousyMenus getInstance(Context context) {
        if(instance == null) {
            instance = new ListFlousyMenus(context);
        }

        return instance;
    }

    public FlousyMenu get(int index) {
        return this.list.get(index);
    }

    public FlousyMenu get(String name) {
        for(FlousyMenu flousyMenu : this.list) {
            if(flousyMenu.getName().compareTo(name) == 0) {
                return flousyMenu;
            }
        }

        return null;
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }
}

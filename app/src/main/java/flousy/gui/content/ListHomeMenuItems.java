package flousy.gui.content;

import android.content.Context;
import android.content.Intent;

import com.example.flousy.UserAccountActivity;
import com.example.flousy.FinancesActivity;
import com.example.flousy.SpendsActivity;
import com.example.flousy.FriendsActivity;
import com.example.flousy.OffersActivity;
import com.example.flousy.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 30/03/2015.
 */
public class ListHomeMenuItems implements Iterable<HomeMenuItem> {

    private static ListHomeMenuItems instance = null;

    private List<HomeMenuItem> list;

    private ListHomeMenuItems(Context context) {
        this.list = new ArrayList<>();

        this.list.add(new HomeMenuItem(
                context.getResources().getString(R.string.activity_spends_name),
                context.getResources().getDrawable(R.drawable.griditem_new),
                context.getResources().getColor(R.color.customGreen),
                new Intent(context, SpendsActivity.class)));

        this.list.add(new HomeMenuItem(
                context.getResources().getString(R.string.activity_finances_name),
                context.getResources().getDrawable(R.drawable.griditem_finances),
                context.getResources().getColor(R.color.customOrange),
                new Intent(context, FinancesActivity.class)));

        this.list.add(new HomeMenuItem(
                context.getResources().getString(R.string.activity_friends_name),
                context.getResources().getDrawable(R.drawable.griditem_friends),
                context.getResources().getColor(R.color.customBlue),
                new Intent(context, FriendsActivity.class)));

        this.list.add(new HomeMenuItem(
                context.getResources().getString(R.string.activity_offers_name),
                context.getResources().getDrawable(R.drawable.griditem_offers),
                context.getResources().getColor(R.color.customPurple),
                new Intent(context, OffersActivity.class)));

        this.list.add(new HomeMenuItem(
                context.getResources().getString(R.string.activity_user_account_name),
                context.getResources().getDrawable(R.drawable.griditem_settings),
                context.getResources().getColor(R.color.customBrown),
                new Intent(context, UserAccountActivity.class)));
    }

    public static synchronized ListHomeMenuItems getInstance(Context context) {
        if (instance == null) {
            instance = new ListHomeMenuItems(context);
        }

        return instance;
    }

    public HomeMenuItem get(int index) {
        return this.list.get(index);
    }

    public HomeMenuItem get(String name) {
        for (HomeMenuItem homeMenuItem : this.list) {
            if (homeMenuItem.getName().equalsIgnoreCase(name)) {
                return homeMenuItem;
            }
        }

        return null;
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public Iterator<HomeMenuItem> iterator() {
        return this.list.iterator();
    }
}
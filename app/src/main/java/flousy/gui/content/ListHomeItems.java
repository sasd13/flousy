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
public class ListHomeItems implements Iterable<HomeItem> {

    private static ListHomeItems instance = null;

    private List<HomeItem> list;

    private ListHomeItems(Context context) {
        this.list = new ArrayList<>();

        this.list.add(new HomeItem(
                context.getResources().getString(R.string.activity_spends_name),
                context.getResources().getDrawable(R.drawable.griditem_new),
                context.getResources().getColor(R.color.customGreen),
                new Intent(context, SpendsActivity.class)));

        this.list.add(new HomeItem(
                context.getResources().getString(R.string.activity_finances_name),
                context.getResources().getDrawable(R.drawable.griditem_finances),
                context.getResources().getColor(R.color.customOrange),
                new Intent(context, FinancesActivity.class)));

        this.list.add(new HomeItem(
                context.getResources().getString(R.string.activity_friends_name),
                context.getResources().getDrawable(R.drawable.griditem_friends),
                context.getResources().getColor(R.color.customBlue),
                new Intent(context, FriendsActivity.class)));

        this.list.add(new HomeItem(
                context.getResources().getString(R.string.activity_offers_name),
                context.getResources().getDrawable(R.drawable.griditem_offers),
                context.getResources().getColor(R.color.customPurple),
                new Intent(context, OffersActivity.class)));

        this.list.add(new HomeItem(
                context.getResources().getString(R.string.activity_user_account_name),
                context.getResources().getDrawable(R.drawable.griditem_settings),
                context.getResources().getColor(R.color.customBrown),
                new Intent(context, UserAccountActivity.class)));
    }

    public static synchronized ListHomeItems getInstance(Context context) {
        if (instance == null) {
            instance = new ListHomeItems(context);
        }

        return instance;
    }

    public HomeItem get(int index) {
        return this.list.get(index);
    }

    public HomeItem get(String name) {
        for(HomeItem homeItem : this.list) {
            if(homeItem.getName().compareTo(name) == 0) {
                return homeItem;
            }
        }

        return null;
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public Iterator<HomeItem> iterator() {
        return this.list.iterator();
    }
}

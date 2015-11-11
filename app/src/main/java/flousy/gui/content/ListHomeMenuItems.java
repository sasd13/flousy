package flousy.gui.content;

import android.content.Context;
import android.content.Intent;

import com.example.flousy.AccountActivity;
import com.example.flousy.SettingActivity;
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
                context.getResources().getString(R.string.activity_account_name),
                context.getResources().getDrawable(R.drawable.griditem_new),
                context.getResources().getColor(R.color.customGreen),
                new Intent(context, AccountActivity.class)));

        this.list.add(new HomeMenuItem(
                context.getResources().getString(R.string.activity_setting_name),
                context.getResources().getDrawable(R.drawable.griditem_settings),
                context.getResources().getColor(R.color.customBrown),
                new Intent(context, SettingActivity.class)));
    }

    public static synchronized ListHomeMenuItems getInstance(Context context) {
        if (instance == null) {
            instance = new ListHomeMenuItems(context);
        }

        return instance;
    }

    @Override
    public Iterator<HomeMenuItem> iterator() {
        return this.list.iterator();
    }
}

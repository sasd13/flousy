package flousy.gui.content;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.diderot.android.flousy.ConsultActivity;
import com.diderot.android.flousy.FinancesActivity;
import com.diderot.android.flousy.FriendsActivity;
import com.diderot.android.flousy.NewActivity;
import com.diderot.android.flousy.OffersActivity;
import com.diderot.android.flousy.R;
import com.diderot.android.flousy.SettingsActivity;

/**
 * Created by Samir on 30/03/2015.
 */
public class ListMenu {

    public class Menu {

        private String name;
        private Drawable image;
        private int color;
        private Intent intent;

        public Menu(String name, Drawable image, int color, Intent intent) {
            setName(name);
            setImage(image);
            setColor(color);
            setIntent(intent);
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Drawable getImage() {
            return this.image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }

        public int getColor() {
            return this.color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public Intent getIntent() {
            return this.intent;
        }

        public void setIntent(Intent intent) {
            this.intent = intent;
            this.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
    }

    private static ListMenu instance = null;

    private Menu[] menus;

    private ListMenu(Context context) {
        menus = new Menu[6];

        menus[0] = new Menu(
                context.getResources().getString(R.string.activity_new_name),
                context.getResources().getDrawable(R.drawable.griditem_new),
                context.getResources().getColor(R.color.customGreen),
                new Intent(context, NewActivity.class));

        menus[1] = new Menu(
                context.getResources().getString(R.string.activity_consult_name),
                context.getResources().getDrawable(R.drawable.griditem_consult),
                context.getResources().getColor(R.color.customRed),
                new Intent(context, ConsultActivity.class));

        menus[2] = new Menu(
                context.getResources().getString(R.string.activity_finances_name),
                context.getResources().getDrawable(R.drawable.griditem_finances),
                context.getResources().getColor(R.color.customOrange),
                new Intent(context, FinancesActivity.class));

        menus[3] = new Menu(
                context.getResources().getString(R.string.activity_friends_name),
                context.getResources().getDrawable(R.drawable.griditem_friends),
                context.getResources().getColor(R.color.customBlue),
                new Intent(context, FriendsActivity.class));

        menus[4] = new Menu(
                context.getResources().getString(R.string.activity_offers_name),
                context.getResources().getDrawable(R.drawable.griditem_offers),
                context.getResources().getColor(R.color.customPurple),
                new Intent(context, OffersActivity.class));

        menus[5] = new Menu(
                context.getResources().getString(R.string.activity_settings_name),
                context.getResources().getDrawable(R.drawable.griditem_settings),
                context.getResources().getColor(R.color.customBrown),
                new Intent(context, SettingsActivity.class));
    }

    public static synchronized ListMenu getInstance(Context context) {
        if(instance == null) {
            instance = new ListMenu(context);
        }

        return instance;
    }

    public Menu get(int index) {
        if(index < 0 || index >= menus.length) {
            return null;
        }

        return menus[index];
    }

    public Menu get(String name) {
        for(Menu menu : menus) {
            if(menu.getName().compareTo(name) == 0) {
                return menu;
            }
        }

        return null;
    }

    public int count() {
        return menus.length;
    }
}

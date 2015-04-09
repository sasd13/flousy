package flousy.gui.content;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by Samir on 06/04/2015.
 */
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

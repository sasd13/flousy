package com.sasd13.flousy.gui.content.homemenu;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class HomeMenuItem {

    private String text;
    private Drawable image;
    private int color;
    private Intent intent;

    public HomeMenuItem(String text, Drawable image, int color, Intent intent) {
        this.text = text;
        this.image = image;
        this.color = color;
        this.intent = intent;
    }

    public String getText() {
        return this.text;
    }

    public Drawable getImage() {
        return this.image;
    }

    public int getColor() {
        return this.color;
    }

    public Intent getIntent() {
        return this.intent;
    }
}

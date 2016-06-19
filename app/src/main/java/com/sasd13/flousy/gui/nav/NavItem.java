package com.sasd13.flousy.gui.nav;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class NavItem {

    private String text;
    private Drawable icon;
    private int color;
    private Intent intent;

    public NavItem(String text, Drawable icon, int color, Intent intent) {
        this.text = text;
        this.icon = icon;
        this.color = color;
        this.intent = intent;
    }

    public String getText() {
        return text;
    }

    public Drawable getIcon() {
        return icon;
    }

    public int getColor() {
        return color;
    }

    public Intent getIntent() {
        return intent;
    }
}

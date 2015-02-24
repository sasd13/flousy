package flousy.util;

import java.util.ArrayList;

/**
 * Created by Samir on 21/02/2015.
 */
public class MenuBox {

    private int menuItemBoxLayoutResource;
    private ArrayList<MenuItemBox> menuItemBoxes;

    public MenuBox(int menuItemBoxLayoutResource) {
        this.menuItemBoxLayoutResource = menuItemBoxLayoutResource;
        this.menuItemBoxes = new ArrayList<MenuItemBox>();
    }

    public int getMenuItemBoxLayoutResource() { return this.menuItemBoxLayoutResource; }

    public ArrayList<MenuItemBox> getMenuItemBoxes() { return this.menuItemBoxes; }

    public boolean addMenuItemBox(MenuItemBox menuItemBox) {
        if(menuItemBox == null) {
            return false;
        }

        boolean contains = false;
        for(int i=0; i<this.menuItemBoxes.size(); i++) {
            if(this.menuItemBoxes.get(i).getTextValue().equals(menuItemBox.getTextValue())) {
                contains = true;
                break;
            }
        }

        if(contains == false) {
            this.menuItemBoxes.add(menuItemBox);
            return true;
        } else {
            return false;
        }
    }

    public MenuItemBox removeMenuItemBox(String menuItemBoxTextValue) {
        MenuItemBox menuItemBox = null;
        for(int i=0; i<this.menuItemBoxes.size(); i++) {
            menuItemBox = this.menuItemBoxes.get(i);
            if(menuItemBox.getTextValue().equals(menuItemBoxTextValue)) {
                this.menuItemBoxes.remove(menuItemBox);
                return menuItemBox;
            }
        }

        return null;
    }
}
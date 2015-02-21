package flousy.util;

import java.util.ArrayList;

/**
 * Created by Samir on 21/02/2015.
 */
public class MenuBox {

    private String menuTitle;
    private ArrayList<MenuItemBox> menuItemBoxes;

    public MenuBox(String menuTitle) {
        this.menuTitle = menuTitle;
        this.menuItemBoxes = new ArrayList<MenuItemBox>();
    }

    public MenuBox(String menuTitle, ArrayList<MenuItemBox> menuItemBoxes) {
        this(menuTitle);

        this.menuItemBoxes = menuItemBoxes;
    }

    public String getMenuTitle() { return this.menuTitle; }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public ArrayList<MenuItemBox> getMenuItemBoxes() { return this.menuItemBoxes; }

    public boolean addMenuItemBox(MenuItemBox menuItemBox) {
        MenuItemBox menuItemBox_temp;
        boolean contains = false;
        for(int i=0; i<this.menuItemBoxes.size(); i++) {
            menuItemBox_temp = this.menuItemBoxes.get(i);
            if(menuItemBox_temp.getTextValue().equals(menuItemBox.getTextValue())) {
                contains = true;
                break;
            }
        }

        if(contains == false) {
            this.menuItemBoxes.add(menuItemBox);
            return true;
        }

        return false;
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

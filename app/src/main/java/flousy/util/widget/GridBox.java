package flousy.util.widget;

import java.util.ArrayList;

/**
 * Created by Samir on 21/02/2015.
 */
public class GridBox {

    private int gridItemBoxLayoutResource;
    private ArrayList<GridItemBox> gridItemBoxes;

    public GridBox(int gridItemBoxLayoutResource) {
        this.gridItemBoxLayoutResource = gridItemBoxLayoutResource;
        this.gridItemBoxes = new ArrayList<GridItemBox>();
    }

    public int getGridItemBoxLayoutResource() { return this.gridItemBoxLayoutResource; }

    public ArrayList<GridItemBox> getGridItemBoxes() { return this.gridItemBoxes; }

    public boolean addGridItemBox(GridItemBox gridItemBox) {
        if(gridItemBox == null) {
            return false;
        }

        boolean contains = false;
        for(int i=0; i<this.gridItemBoxes.size(); i++) {
            if(this.gridItemBoxes.get(i).getText().equals(gridItemBox.getText())) {
                contains = true;
                break;
            }
        }

        if(contains == false) {
            this.gridItemBoxes.add(gridItemBox);
            return true;
        } else {
            return false;
        }
    }

    public GridItemBox removeGridItemBox(CharSequence gridItemBoxText) {
        GridItemBox gridItemBox = null;
        for(int i=0; i<this.gridItemBoxes.size(); i++) {
            gridItemBox = this.gridItemBoxes.get(i);
            if(gridItemBox.getText().equals(gridItemBoxText)) {
                this.gridItemBoxes.remove(gridItemBox);
                return gridItemBox;
            }
        }

        return null;
    }
}
package flousy.util;

import com.diderot.android.flousy.MyActivity;
import com.diderot.android.flousy.R;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

/**
 * <b>MenuItemBox is the class to manage item boxes of a menu</b>
 * <p>
 * A menu item box has :
 * <ul>
 * <li>A background color</li>
 * <li>An image resource</li>
 * <li>A text value</li>
 * </ul>
 * </p>
 *
 * @author  Samir SAID ALI <samir_saidali@yahoo.fr>
 * @version 1.0
 * @since   18/02/2015
 */
public class MenuItemBox {

    public static final int DEFAULT_LAYOUT = R.layout.layout_menuitembox;

    /**
     * MenuItemBox background color
     *
     * <p>type : int (resourceId, example : R.color.black)</p>
     */
    private int backgroundColor;

    /**
     * MenuItemBox image resource
     *
     * <p>type : int (resourceId, example : R.drawable.MenuItemBox)</p>
     * <p>
     * recommanded (maximum) size : 80*80 dp<br/>
     * equivalences :
     * <ul>
     * <li>ldpi : 60*60 px</li>
     * <li>mdpi : 80*80 px</li>
     * <li>hdpi : 120*120 px</li>
     * <li>xhdpi : 160*160 px</li>
     * <li>xxhdpi : 240*240 px</li>
     * <li>xxxhdpi : 320*320 px</li>
     * </ul>
     * </p>
     */
    private int imageResource;

    /**
     * MenuItemBox text value
     *
     * <p>type : CharSequence</p>
     */
    private CharSequence textValue;

    /**
     * MenuItemBox parent layout (container)
     *
     * <p>type : LinearLayout</p>
     */
    private LinearLayout linearLayout;

    /**
     * Container first child
     *
     * <p>type : ImageView</p>
     */
    private ImageView imageView;

    /**
     * Container second child
     *
     * <p>type : TextView</p>
     */
    private TextView textView;

    /**
     * Default constructor
     * <p>Setting only main attributes with default values</p>
     */
    public MenuItemBox() {
        this.backgroundColor = MyActivity.DEFAULT_ACTIVITY_COLOR;
        this.imageResource = R.drawable.menuitembox;
        this.textValue = "Menu";
    }

    /**
     * Constructor
     * <p>Setting main attributes default values and the MenuItemBox container</p>
     *
     * @param linearLayout
     */
    public MenuItemBox(LinearLayout linearLayout) {
        this();

        this.setBox(linearLayout);
    }

    /**
     * Get container
     *
     * @return  LinearLayout
     */
    public LinearLayout getBox() {
        if (this.linearLayout == null) {
            return null;
        }

        return this.linearLayout;
    }

    /**
     * Set container
     *
     * @param linearLayout
     */
    public void setBox(LinearLayout linearLayout) {
        if (linearLayout != null) {
            this.linearLayout = linearLayout;
            this.linearLayout.setBackgroundResource(this.backgroundColor);

            View child;
            for(int i=0; i<this.linearLayout.getChildCount(); i++) {
                child = this.linearLayout.getChildAt(i);

                switch (child.getClass().getSimpleName()) {
                    case "ImageView" :
                        this.imageView = (ImageView) child;
                        this.imageView.setImageResource(this.imageResource);
                        break;
                    case "TextView" :
                        this.textView = (TextView) child;
                        this.textView.setText(this.textValue);
                        break;
                }
            }
        }
    }

    /**
     * Get backgroundColor
     *
     * @return int (resourceId)
     */
    public int getBackgroundColor() { return this.backgroundColor; }

    /**
     * Set backgroundColor
     *
     * @param backgroundColor
     */
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;

        if(this.linearLayout != null) {
            this.linearLayout.setBackgroundResource(this.backgroundColor);
        }
    }

    /**
     * Get imageResource
     *
     * @return int (resourceId)
     */
    public int getImageResource() { return this.imageResource; }

    /**
     * Set imageResource
     *
     * @param imageResource
     */
    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;

        if(this.imageView != null) {
            this.imageView.setImageResource(this.imageResource);
        }
    }

    /**
     * Get textValue
     *
     * @return CharSequence
     */
    public CharSequence getTextValue() { return this.textValue; }

    /**
     * Set textValue
     *
     * @param textValue
     */
    public void setTextValue(CharSequence textValue) {
        this.textValue = textValue;

        if(this.textView != null) {
            this.textView.setText(this.textValue);
        }
    }
}

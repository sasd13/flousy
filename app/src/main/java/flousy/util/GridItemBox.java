package flousy.util;

import com.diderot.android.flousy.MyActivity;
import com.diderot.android.flousy.R;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

/**
 * <b>GridItemBox is the class to manage item boxes of a menu</b>
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
public class GridItemBox {

    /**
     * GridItemBox background color
     *
     * <p>type : int (resourceId, example : R.color.black)</p>
     */
    private int backgroundColor;

    /**
     * GridItemBox image resource
     *
     * <p>type : int (resourceId, example : R.drawable.GridItemBox)</p>
     * <p>
     * recommanded (maximum) size : 64*64 dp<br/>
     * equivalences :
     * <ul>
     * <li>ldpi : 48*48 px</li>
     * <li>mdpi : 64*64 px</li>
     * <li>hdpi : 96*96 px</li>
     * <li>xhdpi : 128*128 px</li>
     * <li>xxhdpi : 192*192 px</li>
     * </ul>
     * </p>
     */
    private int imageResource;

    /**
     * GridItemBox text value
     */
    private CharSequence textValue;

    /**
     * GridItemBox parent layout (container)
     */
    private ViewGroup container;

    /**
     * Container first child
     */
    private ImageView imageView;

    /**
     * Container second child
     */
    private TextView textView;

    /**
     * GridItemBox action on item clicked
     */
    private Intent intent;

    /**
     * Default constructor
     * <p>Setting only main attributes with default values</p>
     */
    public GridItemBox(CharSequence textValue) {
        this.backgroundColor = MyActivity.DEFAULT_ACTIVITY_COLOR;
        this.imageResource = R.drawable.griditembox;
        this.textValue = textValue;
    }

    /**
     * Set container
     *
     * @param container
     */
    public void setContainer(ViewGroup container) {
        if (container != null) {
            this.container = container;
            this.container.setBackgroundResource(this.backgroundColor);

            View child;
            for(int i=0; i<this.container.getChildCount(); i++) {
                child = this.container.getChildAt(i);

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

        if(this.container != null) {
            this.container.setBackgroundResource(this.backgroundColor);
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

    /**
     * Get intent
     *
     * @return Intent
     */
    public Intent getIntent() { return this.intent; }

    /**
     * Set intent
     *
     * @param intent
     */
    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
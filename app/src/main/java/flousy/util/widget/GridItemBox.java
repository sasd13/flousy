package flousy.util.widget;

import com.diderot.android.flousy.MyActivity;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import flousy.util.color.CustomColor;

/**
 * <b>GridItemBox is the class to manage item boxes of a menu</b>
 * <p>
 * A menu item box has :
 * <ul>
 * <li>A color</li>
 * <li>An image</li>
 * <li>A text</li>
 * </ul>
 * </p>
 *
 * @author  Samir SAID ALI <samir_saidali@yahoo.fr>
 * @version 2.0
 * @since   13/03/2015
 */
public class GridItemBox {

    /**
     * GridItemBox color
     *
     * <p>type : CustomColor</p>
     */
    private CustomColor color;

    /**
     * GridItemBox image
     *
     * <p>type : Drawable</p>
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
    private Drawable image;

    /**
     * GridItemBox text
     */
    private CharSequence text;

    /**
     * GridItemBox parent layout (container)
     */
    private ViewGroup containerView;

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
     */
    public GridItemBox() {
        this.color = new CustomColor(MyActivity.APP_COLOR);
        this.image = null;
        this.text = null;
    }

    /**
     * Constructor with params
     */
    public GridItemBox(CharSequence text, Drawable image, CustomColor color) {
        this.color = color;
        this.image = image;
        this.text = text;
    }

    /**
     * Get color
     *
     * @return CustomColor
     */
    public CustomColor getColor() { return this.color; }

    /**
     * Set color
     *
     * @param color
     */
    public void setColor(CustomColor color) {
        this.color = color;

        if(this.containerView != null) {
            this.containerView.setBackgroundColor(this.color.getColor());
        }
    }

    /**
     * Get image
     *
     * @return Drawable
     */
    public Drawable getImage() { return this.image; }

    /**
     * Set image
     *
     * @param image
     */
    public void setImage(Drawable image) {
        this.image = image;

        if(this.imageView != null) {
            this.imageView.setImageDrawable(this.image);
        }
    }

    /**
     * Get text
     *
     * @return CharSequence
     */
    public CharSequence getText() { return this.text; }

    /**
     * Set text
     *
     * @param text
     */
    public void setText(CharSequence text) {
        this.text = text;

        if(this.textView != null) {
            this.textView.setText(this.text);
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

    /**
     * Get containerView
     *
     * @return ViewGroup
     */
    public ViewGroup getContainerView() {
        return this.containerView;
    }

    /**
     * Set containerView
     *
     * @param containerView
     */
    public void setContainerView(ViewGroup containerView) {
        if (containerView != null) {
            this.containerView = containerView;
            this.containerView.setBackgroundColor(this.color.getColor());

            View child;
            for(int i=0; i<this.containerView.getChildCount(); i++) {
                child = this.containerView.getChildAt(i);

                switch (child.getClass().getSimpleName()) {
                    case "ImageView" :
                        this.imageView = (ImageView) child;
                        if(this.image != null) {
                            this.imageView.setImageDrawable(this.image);
                        }
                        break;
                    case "TextView" :
                        this.textView = (TextView) child;
                        if(this.text != null) {
                            this.textView.setText(this.text);
                        }
                        break;
                }
            }
        }
    }
}
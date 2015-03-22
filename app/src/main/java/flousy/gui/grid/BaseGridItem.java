package flousy.gui.grid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * <b>BaseGridItem is the class to manage basic items of a BaseGrid</b>
 * <p>
 * A BaseGrid item box has :
 * <ul>
 * <li>A background backgroundColor</li>
 * <li>An image</li>
 * <li>A text</li>
 * </ul>
 * </p>
 *
 * @author  Samir SAID ALI <samir_saidali@yahoo.fr>
 * @version 2.0
 * @since   13/03/2015
 */
public class BaseGridItem extends GridItem {

    /**
     * BaseGridItem background backgroundColor
     *
     * <p>type : int</p>
     */
    private int backgroundColor;

    /**
     * BaseGridItem image
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
     * BaseGridItem text
     */
    private CharSequence text;

    /**
     * container view first child
     */
    private ImageView imageView;

    /**
     * container view second child
     */
    private TextView textView;

    /**
     * BaseGridItem action on item clicked
     */
    private Intent intent;

    /**
     * Default constructor
     */
    public BaseGridItem() {
        super();
        this.backgroundColor = Color.TRANSPARENT;
        this.image = null;
        this.text = "Item";
        this.intent = null;
    }

    /**
     * Constructor with params
     */
    public BaseGridItem(CharSequence text, Drawable image, int backgroundColor, Intent intent) {
        super();
        this.backgroundColor = backgroundColor;
        this.image = image;
        this.text = text;
        this.intent = intent;
    }

    /**
     * Get backgroundColor
     *
     * @return int
     */
    public int getColor() { return this.backgroundColor; }

    /**
     * Set backgroundColor
     *
     * @param backgroundColor
     */
    public void setColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;

        ViewGroup viewGroup = (ViewGroup) this.getView();

        if(viewGroup != null) {
            viewGroup.setBackgroundColor(this.backgroundColor);
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
            imageView.setImageDrawable(this.image);
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

    @Override
    public void inflate() {
        ViewGroup viewGroup = (ViewGroup) getView();

        viewGroup.setBackgroundColor(this.backgroundColor);

        this.imageView = (ImageView) viewGroup.findViewById(R.id.griditem_base_imageview);
        if(this.image != null) {
            this.imageView.setImageDrawable(this.image);
        }

        this.textView = (TextView) viewGroup.findViewById(R.id.griditem_base_textview);
        if(this.text != null) {
            this.textView.setText(this.text);
        }
    }
}
package flousy.gui.recycler.grid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.diderot.android.flousy.MotherActivity;
import com.diderot.android.flousy.R;

import flousy.gui.color.ColorOnTouchListener;
import flousy.gui.recycler.AbstractRecyclerItem;

/**
 * <b>GridItem is the class to manage basic items of a Grid</b>
 * <p>
 * A Grid item box has :
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
public class GridItem extends AbstractRecyclerItem {

    /**
     * GridItem color
     *
     * <p>type : int</p>
     */
    private int color;

    /**
     * GridItem image
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
     * GridItem text
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
     * GridItem action on item clicked
     */
    private Intent intent;

    /**
     * Constructor with params
     */
    public GridItem() {
        super(R.layout.griditem);

        this.color = 0;
        this.image = null;
        this.text = "Item";
        this.intent = null;
    }

    public GridItem(int layoutResource) {
        super(layoutResource);

        this.color = 0;
        this.image = null;
        this.text = "Item";
        this.intent = null;
    }

    /**
     * Get color
     *
     * @return int
     */
    public int getColor() {
        return this.color;
    }

    /**
     * Set color
     *
     * @param color
     */
    public void setColor(int color) {
        this.color = color;

        if(getView() != null) {
            getView().setBackgroundColor(this.color);
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
    public View inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(getLayoutResource());
        View view = viewStub.inflate();
        if (view == null) {
            return null;
        }

        setView(view);

        if(this.color == 0) {
            this.color = view.getContext().getResources().getColor(MotherActivity.APP_COLOR);
        }
        view.setBackgroundColor(this.color);

        this.imageView = (ImageView) view.findViewById(R.id.griditem_imageview);
        if(this.image == null) {
            this.image = view.getContext().getResources().getDrawable(R.drawable.griditem);
        }
        this.imageView.setImageDrawable(this.image);

        this.textView = (TextView) view.findViewById(R.id.griditem_textview);
        this.textView.setText(this.text);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intent != null) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performClick();
                return false;
            }
        });
        view.setOnTouchListener(new ColorOnTouchListener(this.color));

        return view;
    }
}
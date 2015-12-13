package com.sasd13.flousy.gui.widget.recycler.grid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.sasd13.flousy.R;

import com.sasd13.flousy.gui.color.ColorOnTouchListener;
import com.sasd13.flousy.gui.widget.recycler.RecyclerItem;

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
public class GridItem extends RecyclerItem {

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
    }

    /**
     * Set color
     *
     * @param color
     */
    public void setColor(int color) {
        this.color = color;

        try {
            getView().setBackgroundColor(this.color);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set image
     *
     * @param image
     */
    public void setImage(Drawable image) {
        this.image = image;

        try {
            this.imageView.setImageDrawable(this.image);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set text
     *
     * @param text
     */
    public void setText(CharSequence text) {
        this.text = text;

        try {
            this.textView.setText(this.text);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set intent
     *
     * @param intent
     */
    public void setIntent(Intent intent) {
        this.intent = intent;
        this.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        findViews();
        bindViews();
        setOnClickListener();
        setOnTouchListener();
    }

    private void findViews() {
        this.imageView = (ImageView) getView().findViewById(R.id.griditem_imageview);
        this.textView = (TextView) getView().findViewById(R.id.griditem_textview);
    }

    private void bindViews() {
        if (this.color == 0) {
            this.color = getView().getContext().getResources().getColor(R.color.customOrange);
        }
        setColor(this.color);

        setImage(this.image);
        setText(this.text);
    }

    private void setOnClickListener() {
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    view.getContext().startActivity(intent);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setOnTouchListener() {
        getView().setOnTouchListener(new ColorOnTouchListener(this.color));
    }
}
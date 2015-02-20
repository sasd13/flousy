package flousy.util;

import com.diderot.android.flousy.MyActivity;
import com.diderot.android.flousy.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

/**
 * Created by Samir on 18/02/2015.
 */
public class ImageBox {

    public static final int LAYOUT_DEFAULT_IMAGEBOX = R.layout.layout_imagebox;

    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView textView;

    private int backgroundColor = MyActivity.DEFAULT_ACTIVITY_COLOR;
    private int imageResource = R.drawable.imagebox_new;
    private CharSequence text = "Menu";

    public ImageBox(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;

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
                    this.textView.setText(this.text);
                    break;
            }
        }
    }

    public LinearLayout getBox() { return this.linearLayout; }

    public int getBackgroundColor() { return this.backgroundColor; }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.getBox().setBackgroundResource(backgroundColor);
    }

    public int getImageResource() { return this.imageResource; }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
        this.imageView.setImageResource(imageResource);
    }

    public CharSequence getText() { return this.textView.getText(); }

    public void setText(CharSequence text) {
        this.text = text;
        this.textView.setText(text);
    }
}

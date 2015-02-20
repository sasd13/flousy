package flousy.util;

import com.diderot.android.flousy.MyActivity;
import com.diderot.android.flousy.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

/**
 * Created by Samir on 18/02/2015.
 */
public class ImageBox {

    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView textView;

    private static final int LAYOUT_DEFAULT_IMAGEBOX = R.layout.layout_imagebox;
    private static final int IMAGEBOX_DEFAULT_BACKGROUNDCOLOR = MyActivity.DEFAULT_ACTIVITY_COLOR;
    private static final int IMAGEVIEW_DEFAULT_IMAGERESOURCE = R.drawable.imagebox_new;

    public ImageBox(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.linearLayout = (LinearLayout) inflater.inflate(LAYOUT_DEFAULT_IMAGEBOX, null);

        this.linearLayout.setBackgroundResource(IMAGEBOX_DEFAULT_BACKGROUNDCOLOR);

        View child;
        for(int i=0; i<this.linearLayout.getChildCount(); i++) {
            child = this.linearLayout.getChildAt(i);

            switch (child.getClass().getSimpleName()) {
                case "ImageView" :
                    this.imageView = (ImageView) child;
                    this.imageView.setImageResource(IMAGEVIEW_DEFAULT_IMAGERESOURCE);
                    break;
                case "TextView" :
                    this.textView = (TextView) child;
                    break;
                default:
                    break;
            }
        }
    }

    public LinearLayout getBox() {
        return this.linearLayout;
    }

    public void setBackgroundColor(int resId) {
        this.linearLayout.setBackgroundResource(resId);
    }

    public void setImage(int resId) {
        this.imageView.setImageResource(resId);
    }

    public void setText(CharSequence text) {
        this.textView.setText(text);
    }

    public CharSequence getText() {
        return this.textView.getText();
    }
}

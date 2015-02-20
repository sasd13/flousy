package flousy.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.diderot.android.flousy.MyActivity;
import com.diderot.android.flousy.R;

/**
 * Created by Samir on 18/02/2015.
 */
public class ImageBoxCopy {

    private FrameLayout frameLayout;
    private ImageView imageView;
    private TextView textView;

    private static final int LAYOUT_DEFAULT_IMAGEBOX = R.layout.layout_imagebox;
    private static final int IMAGEBOX_DEFAULT_BACKGROUNDCOLOR = MyActivity.DEFAULT_ACTIVITY_COLOR;
    private static final int IMAGEVIEW_DEFAULT_IMAGERESOURCE = R.drawable.imagebox_new;

    public ImageBoxCopy(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FrameLayout frame = (FrameLayout) inflater.inflate(LAYOUT_DEFAULT_IMAGEBOX, null);

        this.frameLayout = new FrameLayout(context);
        this.frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        this.frameLayout.setPadding(0, 0, 0, 0);
        this.frameLayout.setBackgroundResource(IMAGEBOX_DEFAULT_BACKGROUNDCOLOR);
        this.frameLayout.setVisibility(View.VISIBLE);

        View child;
        for(int i=0; i<frame.getChildCount(); i++) {
            child = frame.getChildAt(i);

            switch (child.getClass().getSimpleName()) {
                case "ImageView" :
                    this.imageView = new ImageView(context);
                    this.imageView.setLayoutParams(child.getLayoutParams());
                    this.imageView.setPadding(0, 0, 0, 0);
                    this.imageView.setImageResource(IMAGEVIEW_DEFAULT_IMAGERESOURCE);
                    this.frameLayout.addView(this.imageView);
                    break;
                case "TextView" :
                    this.textView = new TextView(context);
                    this.textView.setLayoutParams(child.getLayoutParams());
                    this.textView.setPadding(0, 0, 0, 0);
                    this.textView.setText(((TextView) child).getText());
                    this.frameLayout.addView(this.textView);
                    break;
                default:
                    break;
            }
        }
    }

    public FrameLayout getBox() {
        return this.frameLayout;
    }

    public void setBackgroundColor(int resId) {
        this.frameLayout.setBackgroundResource(resId);
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

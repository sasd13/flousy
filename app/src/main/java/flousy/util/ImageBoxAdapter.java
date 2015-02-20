package flousy.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Samir on 20/02/2015.
 */
public class ImageBoxAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ImageBox> imageBoxes;

    public ImageBoxAdapter(Context context, ArrayList<ImageBox> imageBoxes) {
        this.context = context;
        this.imageBoxes = imageBoxes;
    }

    @Override
    public int getCount() {
        return this.imageBoxes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            linearLayout = (LinearLayout) inflater.inflate(ImageBox.LAYOUT_DEFAULT_IMAGEBOX, null);
        }
        else {
            linearLayout = (LinearLayout) convertView;
        }

        ImageBox box = new ImageBox(linearLayout);
        ImageBox box_temp = this.imageBoxes.get(position);
        box.setBackgroundColor(box_temp.getBackgroundColor());
        box.setImageResource(box_temp.getImageResource());
        box.setText(box_temp.getText());

        return linearLayout;
    }
}

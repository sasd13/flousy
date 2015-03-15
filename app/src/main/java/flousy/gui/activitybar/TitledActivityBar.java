package flousy.gui.activitybar;

import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 11/03/2015.
 */
public class TitledActivityBar extends ActivityBar {

    private int textViewId;
    private TextView textView;
    private CharSequence title;

    public TitledActivityBar() {
        super(R.layout.layout_titledactivitybar);
        this.textViewId = R.id.titledactivitybar_textview;
        this.textView = null;
        this.title = "Title";
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        if(this.textView != null) {
            this.textView.setText(this.title);
        }
    }

    @Override
    public void inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(getLayoutResource());

        ViewGroup viewGroup = (ViewGroup) viewStub.inflate();
        setView(viewGroup);

        this.textView = (TextView) viewGroup.findViewById(this.textViewId);
        if(this.textView != null && this.title != null) {
            this.textView.setText(this.title);
        }
    }
}

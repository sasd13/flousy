package flousy.gui.actionbar;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.TextView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 19/03/2015.
 */
public class BaseActionBar extends AbstractActionBar {

    private ImageButton imageButtonPrevious, imageButtonDrawer;
    private TextView textViewTitle, textViewSubTitle;
    private ImageButton imageButtonActionFirst, imageButtonActionSecond;

    public BaseActionBar() {
        super(R.layout.actionbar_base);

        this.imageButtonPrevious = null;
        this.imageButtonDrawer = null;
        this.textViewTitle = null;
        this.imageButtonActionFirst = null;
        this.imageButtonActionSecond = null;
    }

    public ImageButton getImageButtonPrevious() {
        return this.imageButtonPrevious;
    }

    public void setPreviousEnabled(boolean enabled) {
        if(this.imageButtonPrevious != null) {
            this.imageButtonPrevious.setEnabled(enabled);
            if(enabled == true) {
                this.imageButtonPrevious.setVisibility(View.VISIBLE);
            } else {
                this.imageButtonPrevious.setVisibility(View.INVISIBLE);
            }
        }
    }

    public ImageButton getImageButtonDrawer() {
        return this.imageButtonDrawer;
    }

    public void setDrawerEnabled(boolean enabled) {
        if(this.imageButtonDrawer != null) {
            this.getImageButtonDrawer().setEnabled(enabled);
            if(enabled == true) {
                this.imageButtonDrawer.setVisibility(View.VISIBLE);
            } else {
                this.imageButtonDrawer.setVisibility(View.INVISIBLE);
            }
        }
    }

    public TextView getTextViewTitle() {
        return this.textViewTitle;
    }

    public TextView getTextViewSubTitle() {
        return this.textViewSubTitle;
    }

    public void setSubTitleEnabled(boolean enabled) {
        if(this.textViewSubTitle != null) {
            if(enabled == true) {
                ViewGroup.LayoutParams params = this.textViewSubTitle.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                this.textViewSubTitle.setLayoutParams(params);
            } else {
                ViewGroup.LayoutParams params = this.textViewSubTitle.getLayoutParams();
                params.height = 0;
                this.textViewSubTitle.setLayoutParams(params);
            }
        }
    }

    public ImageButton getImageButtonActionFirst() {
        return this.imageButtonActionFirst;
    }

    public void setActionFirstEnabled(boolean enabled) {
        if(this.imageButtonActionFirst != null) {
            this.imageButtonActionFirst.setEnabled(enabled);
            if(enabled == true) {
                this.imageButtonActionFirst.setVisibility(View.VISIBLE);
            } else {
                this.imageButtonActionFirst.setVisibility(View.INVISIBLE);
            }
        }
    }

    public ImageButton getImageButtonActionSecond() {
        return this.imageButtonActionSecond;
    }

    public void setActionSecondEnabled(boolean enabled) {
        if(this.imageButtonActionSecond != null) {
            this.imageButtonActionSecond.setEnabled(enabled);
            if(enabled == true) {
                this.imageButtonActionSecond.setVisibility(View.VISIBLE);
            } else {
                this.imageButtonActionSecond.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(getLayoutResource());

        ViewGroup viewGroup = (ViewGroup) viewStub.inflate();
        setView(viewGroup);

        this.imageButtonPrevious = (ImageButton) viewGroup.findViewById(R.id.actionbar_imagebutton_previous);
        this.imageButtonDrawer = (ImageButton) viewGroup.findViewById(R.id.actionbar_imagebutton_drawer);

        this.textViewTitle = (TextView) viewGroup.findViewById(R.id.actionbar_textview_title);
        this.textViewSubTitle = (TextView) viewGroup.findViewById(R.id.actionbar_textview_subtitle);

        this.imageButtonActionFirst = (ImageButton) viewGroup.findViewById(R.id.actionbar_imagebutton_action_first);
        this.imageButtonActionSecond = (ImageButton) viewGroup.findViewById(R.id.actionbar_imagebutton_action_second);
    }
}

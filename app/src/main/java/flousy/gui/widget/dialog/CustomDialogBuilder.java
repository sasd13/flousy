package flousy.gui.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.example.flousy.R;

public class CustomDialogBuilder extends AlertDialog.Builder {

    public static final int TYPE_LOAD = 0;
    public static final int TYPE_ONEBUTTON_OK = 1;
    public static final int TYPE_TWOBUTTON_YESNO = 2;
    public static final int TYPE_TWOBUTTON_OKCANCEL = 3;

    private Context context;
    private int dialogType;

    public CustomDialogBuilder(Context context, int dialogType) {
        super(context);

        this.context = context;
        this.dialogType = dialogType;
    }

    @NonNull
    @Override
    public AlertDialog create() {
        if (this.dialogType == TYPE_LOAD) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            setView(inflater.inflate(R.layout.customdialog_load, null));
        }

        return super.create();
    }

    @NonNull
    @Override
    public CustomDialogBuilder setTitle(CharSequence title) {
        super.setTitle(title);

        return this;
    }

    @NonNull
    @Override
    public CustomDialogBuilder setMessage(CharSequence message) {
        super.setMessage(message);

        return this;
    }

    public CustomDialogBuilder setNeutralButton(DialogInterface.OnClickListener listener) {
        super.setNeutralButton(R.string.alertdialog_button_ok, listener);

        return this;
    }

    public CustomDialogBuilder setPositiveButton(DialogInterface.OnClickListener listener) {
        switch (this.dialogType) {
            case TYPE_TWOBUTTON_YESNO :
                super.setPositiveButton(R.string.alertdialog_button_yes, listener);
                break;
            case TYPE_TWOBUTTON_OKCANCEL :
                super.setPositiveButton(R.string.alertdialog_button_ok, listener);
                break;
        }

        return this;
    }

    public CustomDialogBuilder setNegativeButton(DialogInterface.OnClickListener listener) {
        switch (this.dialogType) {
            case TYPE_TWOBUTTON_YESNO:
                super.setNegativeButton(R.string.alertdialog_button_no, listener);
                break;
            case TYPE_TWOBUTTON_OKCANCEL:
                super.setNegativeButton(R.string.alertdialog_button_cancel, listener);
                break;
        }

        return this;
    }
}

package flousy.util.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 09/03/2015.
 */
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
    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getDialogType() {
        return this.dialogType;
    }

    public void setDialogType(int dialogType) {
        this.dialogType = dialogType;
    }

    @NonNull
    @Override
    public AlertDialog create() {
        if(this.dialogType == TYPE_LOAD) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            super.setView(inflater.inflate(R.layout.layout_customdialog_load, null));
        }

        return super.create();
    }

    @Override
    public CustomDialogBuilder setTitle(int titleId) {
        super.setTitle(titleId);

        return this;
    }

    @Override
    public CustomDialogBuilder setMessage(int messageId) {
        super.setMessage(messageId);

        return this;
    }

    public CustomDialogBuilder setNeutralButton(DialogInterface.OnClickListener listener) {
        super.setNeutralButton(R.string.alertdialog_button_ok, listener);

        return this;
    }

    public CustomDialogBuilder setPositiveButton(DialogInterface.OnClickListener listener) {
        if(this.dialogType == TYPE_TWOBUTTON_YESNO) {
            super.setPositiveButton(R.string.alertdialog_button_yes, listener);
        } else {
            super.setPositiveButton(R.string.alertdialog_button_ok, listener);
        }

        return this;
    }

    public AlertDialog.Builder setNegativeButton(DialogInterface.OnClickListener listener) {
        if(this.dialogType == TYPE_TWOBUTTON_YESNO) {
            super.setNegativeButton(R.string.alertdialog_button_no, listener);
        } else {
            super.setNegativeButton(R.string.alertdialog_button_cancel, listener);
        }

        return this;
    }
}

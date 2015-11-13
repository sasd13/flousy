package flousy.gui.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class CustomDialog {

    public static void showOkDialog(Context context, String title, String message) {
        showOkDialog(context, title, message, null);
    }

    public static void showOkDialog(Context context, String title, String message, DialogInterface.OnClickListener neutralListener) {
        CustomDialogBuilder builder = new CustomDialogBuilder(context, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
        builder.setTitle(title)
                .setMessage(message)
                .setNeutralButton(neutralListener);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showYesNoDialog(Context context, String title, String message, DialogInterface.OnClickListener positiveListener) {
        CustomDialogBuilder builder = new CustomDialogBuilder(context, CustomDialogBuilder.TYPE_TWOBUTTON_YESNO);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveListener)
                .setNegativeButton(null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showOkCancelDialog(Context context, String title, String message, DialogInterface.OnClickListener positiveListener) {
        CustomDialogBuilder builder = new CustomDialogBuilder(context, CustomDialogBuilder.TYPE_TWOBUTTON_OKCANCEL);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveListener)
                .setNegativeButton(null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

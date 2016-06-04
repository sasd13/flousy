package com.sasd13.flousy.gui.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.dialog.Dialog;
import com.sasd13.androidex.util.KeyboardHelper;

/**
 * Created by Samir on 04/06/2016.
 */
public class EditorDialog extends Dialog {

    public interface OnEditorActionListener {
        void doAction(EditorDialog editorDialog, String text);
    }

    public interface OnClickListener {
        void doAction(EditorDialog editorDialog, String text);
    }

    private EditText editText;
    private OnClickListener onButtonPositiveClickListener;

    public EditorDialog(Context context) {
        super(context);

        editText = new EditText(builder.getContext());
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 0, 10, 0);
        editText.setLayoutParams(params);
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setMessage(String message) {
        builder.setMessage(message);
    }

    public void setIcon(Drawable icon) {
        builder.setIcon(icon);
    }

    public void setHint(String hint) {
        editText.setHint(hint);
    }

    public void setOnEditorActionListener(final OnEditorActionListener onEditorActionListener) {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (onEditorActionListener != null) {
                        onEditorActionListener.doAction(EditorDialog.this, textView.getText().toString());
                    }

                    return true;
                }

                return false;
            }
        });
    }

    public void setOnButtonPositiveClickListener(OnClickListener onButtonPositiveClickListener) {
        this.onButtonPositiveClickListener = onButtonPositiveClickListener;
    }

    @Override
    protected void build() {
        editText.requestFocus();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        builder.setView(editText);
        builder.setPositiveButton(
                builder.getContext().getResources().getString(com.sasd13.androidex.R.string.button_ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        KeyboardHelper.hide(editText);

                        if (onButtonPositiveClickListener != null) {
                            onButtonPositiveClickListener.doAction(EditorDialog.this, editText.getText().toString());
                        }
                    }
                }
        );
        builder.setNegativeButton(
                builder.getContext().getResources().getString(com.sasd13.androidex.R.string.button_cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        KeyboardHelper.hide(editText);
                        dismiss();
                    }
                }
        );
    }
}

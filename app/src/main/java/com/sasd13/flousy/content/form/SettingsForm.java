package com.sasd13.flousy.content.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.form.TextModel;
import com.sasd13.flousy.R;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class SettingsForm extends Form {

    private TextModel textModelFirstName, textModelLastName, textModelEmail;

    public SettingsForm(Context context) {
        super(context);
    }

    public RecyclerHolder fabricate() {
        String title = context.getResources().getString(R.string.title_identity);

        textModelFirstName = new TextModel();
        textModelFirstName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        textModelFirstName.setLabel(context.getResources().getString(R.string.label_firstname));
        textModelFirstName.setHint(textModelFirstName.getLabel().toLowerCase());
        holder.add(title, textModelFirstName);

        textModelLastName = new TextModel();
        textModelLastName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        textModelLastName.setLabel(context.getResources().getString(R.string.label_lastname));
        textModelLastName.setHint(textModelLastName.getLabel().toLowerCase());
        holder.add(title, textModelLastName);

        title = context.getResources().getString(R.string.drawer_header_account);

        textModelEmail = new TextModel();
        textModelEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        textModelEmail.setLabel(context.getResources().getString(R.string.label_email));
        textModelEmail.setHint(textModelEmail.getLabel().toLowerCase());
        holder.add(title, textModelEmail);

        return holder;
    }

    public String getFirstName() {
        return textModelFirstName.getValue();
    }
    
    public void setFirstName(String firstName) {
        textModelFirstName.setValue(firstName);
    }

    public String getLastName() {
        return textModelLastName.getValue();
    }

    public void setLastName(String lastName) {
        textModelLastName.setValue(lastName);
    }

    public String getEmail() {
        return textModelEmail.getValue();
    }

    public void setEmail(String email) {
        textModelEmail.setValue(email);
    }
}

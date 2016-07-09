package com.sasd13.flousy.content.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.form.BooleanItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.PasswordItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.flousy.R;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class SignForm extends Form {

    private TextItemModel textItemModelFirstName, textItemModelLastName, textItemModelEmail;
    private PasswordItemModel passwordItemModel;
    private BooleanItemModel booleanItemModelTerms;

    public SignForm(Context context) {
        super(context);
    }

    public RecyclerHolder fabricate() {
        String title = context.getResources().getString(R.string.title_identity);

        textItemModelFirstName = new TextItemModel();
        textItemModelFirstName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        textItemModelFirstName.setLabel(context.getResources().getString(R.string.label_firstname));
        textItemModelFirstName.setHint(textItemModelFirstName.getLabel().toLowerCase());
        holder.add(title, textItemModelFirstName);

        textItemModelLastName = new TextItemModel();
        textItemModelLastName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        textItemModelLastName.setLabel(context.getResources().getString(R.string.label_lastname));
        textItemModelLastName.setHint(textItemModelLastName.getLabel().toLowerCase());
        holder.add(title, textItemModelLastName);

        title = context.getResources().getString(R.string.drawer_header_account);

        textItemModelEmail = new TextItemModel();
        textItemModelEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        textItemModelEmail.setLabel(context.getResources().getString(R.string.label_email));
        textItemModelEmail.setHint(textItemModelEmail.getLabel().toLowerCase());
        holder.add(title, textItemModelEmail);

        passwordItemModel = new PasswordItemModel();
        passwordItemModel.setLabel(context.getResources().getString(R.string.label_password));
        holder.add(title, passwordItemModel);

        booleanItemModelTerms = new BooleanItemModel();
        booleanItemModelTerms.setLabel(context.getResources().getString(R.string.label_terms));
        holder.add(title, booleanItemModelTerms);

        return holder;
    }

    public String getFirstName() {
        return textItemModelFirstName.getValue();
    }
    
    public void setFirstName(String firstName) {
        textItemModelFirstName.setValue(firstName);
    }

    public String getLastName() {
        return textItemModelLastName.getValue();
    }

    public void setLastName(String lastName) {
        textItemModelLastName.setValue(lastName);
    }

    public String getEmail() {
        return textItemModelEmail.getValue();
    }

    public void setEmail(String email) {
        textItemModelEmail.setValue(email);
    }

    public String getPassword() {
        return passwordItemModel.getValue();
    }

    public boolean areTermsAccepted() {
        return booleanItemModelTerms.getValue();
    }
}

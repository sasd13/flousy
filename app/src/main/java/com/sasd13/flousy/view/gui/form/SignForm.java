package com.sasd13.flousy.view.gui.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.BooleanItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.PasswordItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.flousy.R;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class SignForm extends Form {

    private TextItemModel modelIntermediary, modelFirstName, modelLastName, modelEmail, modelUsername;
    private PasswordItemModel modelPassword;
    private BooleanItemModel modelTerms;

    public SignForm(Context context) {
        super(context);

        String title = context.getString(R.string.title_identity);

        modelIntermediary = new TextItemModel(InputType.TYPE_CLASS_TEXT);
        modelIntermediary.setLabel(context.getString(R.string.label_intermediary));
        modelIntermediary.setHint(modelIntermediary.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelIntermediary));

        modelFirstName = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelFirstName.setLabel(context.getString(R.string.label_firstname));
        modelFirstName.setHint(modelFirstName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelFirstName));

        modelLastName = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelLastName.setLabel(context.getString(R.string.label_lastname));
        modelLastName.setHint(modelLastName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelLastName));

        modelEmail = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        modelEmail.setLabel(context.getString(R.string.label_email));
        modelEmail.setHint(modelEmail.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelEmail));

        title = context.getString(R.string.drawer_header_account);

        modelUsername = new TextItemModel(InputType.TYPE_CLASS_TEXT);
        modelUsername.setLabel(context.getString(R.string.label_username));
        modelUsername.setHint(modelUsername.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelUsername));

        modelPassword = new PasswordItemModel();
        modelPassword.setLabel(context.getString(R.string.label_password));
        holder.add(title, new RecyclerHolderPair(modelPassword));

        modelTerms = new BooleanItemModel();
        modelTerms.setLabel(context.getString(R.string.label_terms));
        holder.add(title, new RecyclerHolderPair(modelTerms));
    }

    public String getIntermediary() throws FormException {
        if (StringUtils.isBlank(modelIntermediary.getValue())) {
            throw new FormException(context, R.string.form_sign_message_error_intermediary);
        }

        return modelIntermediary.getValue().trim();
    }

    public String getFirstName() throws FormException {
        if (StringUtils.isBlank(modelFirstName.getValue())) {
            throw new FormException(context, R.string.form_sign_message_error_firstname);
        }

        return modelFirstName.getValue().trim();
    }

    public String getLastName() throws FormException {
        if (StringUtils.isBlank(modelLastName.getValue())) {
            throw new FormException(context, R.string.form_sign_message_error_lastname);
        }

        return modelLastName.getValue().trim();
    }

    public String getEmail() throws FormException {
        if (StringUtils.isBlank(modelEmail.getValue())) {
            throw new FormException(context, R.string.form_sign_message_error_email);
        }

        return modelEmail.getValue().trim();
    }

    public String getUsername() throws FormException {
        if (StringUtils.isBlank(modelUsername.getValue())) {
            throw new FormException(context, R.string.form_sign_message_error_username);
        }

        return modelUsername.getValue();
    }

    public String getPassword() throws FormException {
        if (StringUtils.isBlank(modelPassword.getValue())) {
            throw new FormException(context, R.string.form_sign_message_error_password);
        }

        return modelPassword.getValue();
    }

    public boolean isTermsAccepted() {
        return modelTerms.getValue();
    }
}

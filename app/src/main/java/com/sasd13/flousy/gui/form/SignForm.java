package com.sasd13.flousy.gui.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.BooleanItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.PasswordItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Customer;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class SignForm extends Form {

    private TextItemModel modelFirstName, modelLastName, modelEmail;
    private PasswordItemModel modelPassword;
    private BooleanItemModel modelTerms;

    public SignForm(Context context) {
        super(context);

        String title = context.getResources().getString(R.string.title_identity);

        modelFirstName = new TextItemModel();
        modelFirstName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelFirstName.setLabel(context.getResources().getString(R.string.label_firstname));
        modelFirstName.setHint(modelFirstName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelFirstName));

        modelLastName = new TextItemModel();
        modelLastName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelLastName.setLabel(context.getResources().getString(R.string.label_lastname));
        modelLastName.setHint(modelLastName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelLastName));

        title = context.getResources().getString(R.string.drawer_header_account);

        modelEmail = new TextItemModel();
        modelEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        modelEmail.setLabel(context.getResources().getString(R.string.label_email));
        modelEmail.setHint(modelEmail.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelEmail));

        modelPassword = new PasswordItemModel();
        modelPassword.setLabel(context.getResources().getString(R.string.label_password));
        holder.add(title, new RecyclerHolderPair(modelPassword));

        modelTerms = new BooleanItemModel();
        modelTerms.setLabel(context.getResources().getString(R.string.label_terms));
        holder.add(title, new RecyclerHolderPair(modelTerms));
    }

    public Customer getEditable() throws FormException {
        validForm();

        Customer customer = new Customer();

        customer.setFirstName(modelFirstName.getValue().trim());
        customer.setLastName(modelLastName.getValue().trim());
        customer.setEmail(modelEmail.getValue().trim());

        return customer;
    }

    private void validForm() throws FormException {
        validFirstName();
        validLastName();
        validEmail();
        validPassword();
        validTerms();
    }

    private void validFirstName() throws FormException {
        if (StringUtils.isBlank(modelFirstName.getValue())) {
            throw new FormException(context, R.string.form_sign_message_error_firstname);
        }
    }

    private void validLastName() throws FormException {
        if (StringUtils.isBlank(modelLastName.getValue())) {
            throw new FormException(context, R.string.form_sign_message_error_lastname);
        }
    }

    private void validEmail() throws FormException {
        if (StringUtils.isBlank(modelEmail.getValue())) {
            throw new FormException(context, R.string.form_sign_message_error_email);
        }
    }

    private void validPassword() throws FormException {
        if (StringUtils.isBlank(modelPassword.getValue())) {
            throw new FormException(context, R.string.form_sign_message_error_password);
        }
    }

    private void validTerms() throws FormException {
        if (!modelTerms.getValue()) {
            throw new FormException(context, R.string.form_sign_message_error_terms);
        }
    }

    public String getPassword() throws FormException {
        validPassword();

        return modelPassword.getValue();
    }
}

package com.sasd13.flousy.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Customer;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class SettingsForm extends Form {

    private TextItemModel modelFirstName, modelLastName, modelEmail;

    public SettingsForm(Context context) {
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
    }

    public void bindCustomer(Customer customer) {
        modelFirstName.setValue(customer.getFirstName());
        modelLastName.setValue(customer.getLastName());
        modelEmail.setValue(customer.getEmail());
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
        if (modelFirstName.getValue() == null
                || modelFirstName.getValue().trim().isEmpty()) {
            throw new FormException(context.getResources().getString(R.string.form_settings_message_error_firstname));
        }

        if (modelLastName.getValue() == null
                || modelLastName.getValue().trim().isEmpty()) {
            throw new FormException(context.getResources().getString(R.string.form_settings_message_error_lastname));
        }

        if (modelEmail.getValue() == null
                || modelEmail.getValue().trim().isEmpty()) {
            throw new FormException(context.getResources().getString(R.string.form_settings_message_error_email));
        }
    }

    public String getEmail() throws FormException {
        if (modelEmail.getValue() == null
                || modelEmail.getValue().trim().isEmpty()) {
            throw new FormException(context.getResources().getString(R.string.form_settings_message_error_email));
        }

        return modelEmail.getValue().trim();
    }
}

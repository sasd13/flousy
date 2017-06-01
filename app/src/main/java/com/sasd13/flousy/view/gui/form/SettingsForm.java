package com.sasd13.flousy.view.gui.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Customer;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class SettingsForm extends Form {

    private TextItemModel modelFirstName, modelLastName, modelEmail;

    public SettingsForm(Context context) {
        super(context);

        String title = context.getString(R.string.title_identity);

        modelFirstName = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelFirstName.setLabel(context.getString(R.string.label_firstname));
        modelFirstName.setHint(modelFirstName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelFirstName));

        modelLastName = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelLastName.setLabel(context.getString(R.string.label_lastname));
        modelLastName.setHint(modelLastName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelLastName));

        title = context.getString(R.string.drawer_header_account);

        modelEmail = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        modelEmail.setLabel(context.getString(R.string.label_email));
        modelEmail.setHint(modelEmail.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelEmail));
    }

    public void bindCustomer(Customer customer) {
        modelFirstName.setValue(customer.getFirstName());
        modelLastName.setValue(customer.getLastName());
        modelEmail.setValue(customer.getEmail());
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
}

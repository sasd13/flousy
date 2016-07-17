package com.sasd13.flousy.content.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.BooleanItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.EditTextItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.PasswordItemModel;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Customer;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class SignForm extends Form {

    private EditTextItemModel modelFirstName, modelLastName, modelEmail;
    private PasswordItemModel modelPassword;
    private BooleanItemModel modelTerms;

    public SignForm(Context context) {
        String title = context.getResources().getString(R.string.title_identity);

        modelFirstName = new EditTextItemModel();
        modelFirstName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelFirstName.setLabel(context.getResources().getString(R.string.label_firstname));
        modelFirstName.setHint(modelFirstName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelFirstName));

        modelLastName = new EditTextItemModel();
        modelLastName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelLastName.setLabel(context.getResources().getString(R.string.label_lastname));
        modelLastName.setHint(modelLastName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelLastName));

        title = context.getResources().getString(R.string.drawer_header_account);

        modelEmail = new EditTextItemModel();
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

    public Customer getEditable() {
        Customer customer = new Customer();

        customer.setFirstName(modelFirstName.getValue());
        customer.setLastName(modelLastName.getValue());
        customer.setEmail(modelEmail.getValue());

        return customer;
    }

    public String getPassword() {
        return modelPassword.getValue();
    }

    public boolean areTermsAccepted() {
        return modelTerms.getValue();
    }
}

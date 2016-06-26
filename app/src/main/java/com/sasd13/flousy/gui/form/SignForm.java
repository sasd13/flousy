package com.sasd13.flousy.gui.form;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerModel;
import com.sasd13.androidex.gui.widget.recycler.form.BooleanModel;
import com.sasd13.androidex.gui.widget.recycler.form.FormModel;
import com.sasd13.androidex.gui.widget.recycler.form.PasswordModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class SignForm {

    private RecyclerHolder holder;
    private TextModel textModelFirstName, textModelLastName, textModelEmail;
    private PasswordModel passwordModel;
    private BooleanModel booleanModelTerms;

    public SignForm() {
        holder = new RecyclerHolder();

        List<RecyclerModel> formModels = new ArrayList<>();

        textModelFirstName = new TextModel();
        textModelFirstName.setLabel("First name");
        textModelFirstName.setHint(textModelFirstName.getLabel().toLowerCase());
        formModels.add(textModelFirstName);

        textModelLastName = new TextModel();
        textModelLastName.setLabel("Last name");
        textModelLastName.setHint(textModelLastName.getLabel().toLowerCase());
        formModels.add(textModelLastName);

        holder.add("Identité", formModels.toArray(new FormModel[formModels.size()]));

        formModels.clear();

        textModelEmail = new TextModel();
        textModelEmail.setLabel("Email");
        textModelEmail.setHint(textModelEmail.getLabel().toLowerCase());
        formModels.add(textModelEmail);

        passwordModel = new PasswordModel();
        passwordModel.setLabel("Password");
        formModels.add(passwordModel);

        booleanModelTerms = new BooleanModel();
        booleanModelTerms.setLabel("Terms of use");
        formModels.add(booleanModelTerms);

        holder.add("Compte", formModels.toArray(new FormModel[formModels.size()]));
    }

    public RecyclerHolder getHolder() {
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

    public String getPassword() {
        return passwordModel.getValue();
    }

    public boolean getTerms() {
        return booleanModelTerms.getValue();
    }
}
